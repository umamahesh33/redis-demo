package com.example.redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService{

    private static String USER_PREFIX_KEY="USER::";

    @Autowired
    RedisTemplate<String,UserModel> redisTemplate;

    public void createUser(UserModel userModel){
        redisTemplate.opsForValue().set(USER_PREFIX_KEY+userModel.getId(),userModel);
    }

    public void updateUser(int id,UserModel userModel){
        redisTemplate.opsForValue().getAndSet(USER_PREFIX_KEY+id,userModel);
    }

    public List<UserModel> getAllUsers(){
        Set<String> userKeys= userKeys=redisTemplate.keys(USER_PREFIX_KEY+"*");
        return userKeys.stream()
                        .map(key->(UserModel)redisTemplate.opsForValue().get(key))
                        .collect(Collectors.toList());
    }

    public UserModel getUserById(int id){
        return redisTemplate.opsForValue().get(USER_PREFIX_KEY+id);
    }


    public String deleteUserById(int id){
        while(true){
            redisTemplate.delete(USER_PREFIX_KEY+id);
            return "entry deleted SUCCESSFULLY";
        }
    }

}
