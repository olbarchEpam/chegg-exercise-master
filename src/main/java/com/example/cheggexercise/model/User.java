package com.example.cheggexercise.model;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String uId;
    private String data;
    private Timestamp timestamp;

    public User(UserDao userDao){
        this.uId = userDao.getUId();
        this.data = userDao.getData();
        this.timestamp = userDao.getTimestamp();
    }
}
