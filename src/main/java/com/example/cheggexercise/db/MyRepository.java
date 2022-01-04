package com.example.cheggexercise.db;

import com.example.cheggexercise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRepository extends JpaRepository<User,String> {
}
