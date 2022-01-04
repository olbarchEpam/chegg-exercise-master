package com.example.cheggexercise.model;

import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class UserDao {
    private String uId;
    private String data;
    private Timestamp timestamp;

    public UserDao(UserDto userDto){
        this.uId = userDto.getUId();
        this.data = userDto.getData();
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
