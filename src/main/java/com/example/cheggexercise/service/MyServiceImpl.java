package com.example.cheggexercise.service;

import com.example.cheggexercise.db.MyRepository;
import com.example.cheggexercise.model.User;
import com.example.cheggexercise.model.UserDao;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
public class MyServiceImpl implements MyService {

    private final MyRepository myRepository;
    private Cache<String, ArrayList<Timestamp>> map;

    public MyServiceImpl(MyRepository myRepository){
        this.myRepository = myRepository;
        this.map = CacheBuilder.newBuilder()
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public int getAmountOfRequest(String uid) {
        ArrayList<Timestamp> timestamps = map.getIfPresent(uid);
        Timestamp time = Timestamp.valueOf(LocalDateTime.now().minusHours(1));
        if(timestamps != null) {
            int index = binarySearch(timestamps, time);
            return timestamps.subList(index, timestamps.size() - 1).size();
        }
        return 0;
    }


    @Override
    public User insert(UserDao userDao) {
        String uId = userDao.getUId();
        Timestamp time = userDao.getTimestamp();
        ArrayList<Timestamp> userTimestamps = map.getIfPresent(uId);
        if (userTimestamps != null) {
            userTimestamps.add(time);
        }
        else {
            ArrayList<Timestamp> timestamps = new ArrayList<>();
            timestamps.add(time);
            map.put(uId, timestamps);
        }
        return myRepository.save(new User(userDao) );
    }

    private int binarySearch(ArrayList<Timestamp> timestamps, Timestamp time) {
        return binarySearch(timestamps, 0, timestamps.size() - 1, time);
    }

    private int binarySearch(ArrayList<Timestamp> timestamps, int leftIndex, int rightIndex, Timestamp time) {
        if (rightIndex >= leftIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            if (timestamps.get(mid) == time)
                return mid;
            if (timestamps.get(mid).after(time))
                return binarySearch(timestamps, leftIndex, mid - 1, time);
            return binarySearch(timestamps, mid + 1, rightIndex, time);
        }
        return 0;
    }
}
