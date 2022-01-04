package com.example.cheggexercise.controller;



import com.example.cheggexercise.model.User;
import com.example.cheggexercise.model.UserDao;
import com.example.cheggexercise.model.UserDto;
import com.example.cheggexercise.service.MyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @GetMapping("/get/{uid}")
    int get(@PathVariable String uid){
        return myService.getAmountOfRequest(uid);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/insert")
    User insert(@RequestBody UserDto userDto) {
        return myService.insert(new UserDao(userDto));
    }

}
