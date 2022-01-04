package com.example.cheggexercise.service;

import com.example.cheggexercise.model.User;
import com.example.cheggexercise.model.UserDao;

public interface MyService {
    int getAmountOfRequest(String uid);
    User insert(UserDao userRequest);
}
