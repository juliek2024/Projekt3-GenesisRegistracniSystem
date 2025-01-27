package Projekt3_GenesisRegistracniSystem.controller;

import Projekt3_GenesisRegistracniSystem.model.RegisterUserException;
import Projekt3_GenesisRegistracniSystem.model.User;
import Projekt3_GenesisRegistracniSystem.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")

public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) throws RegisterUserException {
        return registerService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable("id") int id, @RequestParam(name = "detail", required = false) String detail) throws RegisterUserException {
        return registerService.getUserInfo(id, detail);
    }

    @GetMapping
    public List<User> getUsersInfo(@RequestParam(name = "detail", required = false) String detail) throws Exception {
        return registerService.getUsersInfo(detail);
    }

    @PutMapping
    public User amendUserInfo(@RequestBody User updateUser) throws Exception {
        return registerService.amendUserInfo(updateUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) throws Exception {
        return registerService.deleteUser(id);
    }
}
