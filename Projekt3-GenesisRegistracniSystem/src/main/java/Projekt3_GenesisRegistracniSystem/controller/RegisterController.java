package Projekt3_GenesisRegistracniSystem.controller;

import Projekt3_GenesisRegistracniSystem.model.RegisterUserException;
import Projekt3_GenesisRegistracniSystem.model.User;
import Projekt3_GenesisRegistracniSystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")

public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/user")
    public String createUser(@RequestBody User user) throws RegisterUserException {
        return registerService.createUser(user);
    }

    @GetMapping("/user/{id}")
    public User getUserInfo(@PathVariable("id") int ID, @RequestParam(name = "detail", required = false) String detail) throws RegisterUserException {
        return registerService.getUserInfo(ID, detail);
    }

    @GetMapping("/users")
    public List<User> getUsersInfo(@RequestParam(name = "detail", required = false) String detail) throws Exception {
        return registerService.getUsersInfo(detail);
    }

    @PutMapping("/users")
    public User amendUserInfo(@RequestBody User updateUser) throws Exception {
        return registerService.amendUserInfo(updateUser);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") int ID) throws Exception {
        return registerService.deleteUser(ID);
    }
}