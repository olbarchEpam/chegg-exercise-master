package com.example.cheggexercise.service;

import com.example.cheggexercise.model.User;
import com.example.cheggexercise.model.UserDao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class OlegImpl implements MyService {

    private HashMap<String, TreeMap<Timestamp, Integer>> cache = new HashMap<>();

    @Override
    public int getAmountOfRequest(String uid) {
        TreeMap<Timestamp, Integer> uidActions = cache.get(uid);
        if (uidActions == null || uidActions.isEmpty()) {
            return 0;
        }
        LocalDateTime currentLDT = LocalDateTime.now();
        Timestamp currentTime = Timestamp.valueOf(currentLDT);
        LocalDateTime lastHourLDT = currentLDT.minusHours(1);
        Timestamp lastHourTime = Timestamp.valueOf(lastHourLDT);
        SortedMap<Timestamp, Integer> timestamps = uidActions.subMap(lastHourTime, currentTime);
        return timestamps.values()
                .stream()
                .mapToInt(t -> t)
                .sum();
    }

    @Override
    public User insert(UserDao userRequest) {
        String uid = userRequest.getUId();
        Timestamp timestamp = userRequest.getTimestamp();
        TreeMap<Timestamp, Integer> defaultValue = new TreeMap<>(Comparator.naturalOrder());
        TreeMap<Timestamp, Integer> userCache = cache.getOrDefault(uid, defaultValue);
        userCache.put(timestamp, userCache.getOrDefault(timestamp, 0) + 1);
        cache.put(uid, userCache);
        return new User(userRequest);
    }
}
