package com.example.redisdemo;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController{

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserModel userModel){
        userService.createUser(userModel);
    }

    @PostMapping("/update")
    public void updateUser(@RequestParam("id")int id,@RequestBody UserModel userModel){
        userService.updateUser(id,userModel);
    }

    @GetMapping("/all")
    public List<UserModel> getAllUsers(){
        return  userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable("id") int id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id")int id){
        return userService.deleteUserById(id);
    }
}
