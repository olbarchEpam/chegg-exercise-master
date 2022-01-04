package com.example.cheggexercise.service;

import com.example.cheggexercise.model.UserDao;
import com.example.cheggexercise.model.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OlegImplTest {
    @InjectMocks
    OlegImpl olegImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Random random = new Random();
        List<UserDao> users = IntStream.range(0, 100).mapToObj(e -> new UserDao(new UserDto("", ""))).collect(Collectors.toList());
        for (UserDao user : users) {
            Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now().minusHours(1).plusMinutes(random.nextInt(10)));
            user.setTimestamp(timestamp);
            user.setData("some data");
            user.setUId(String.valueOf(random.nextInt(5)));
            olegImpl.insert(user);
        }
    }

    @Test
    public void testGetAmountOfRequest() throws Exception {

    }

    @Test
    public void testInsert() throws Exception {

    }
}
