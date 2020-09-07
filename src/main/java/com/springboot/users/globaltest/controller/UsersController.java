package com.springboot.users.globaltest.controller;

import com.springboot.users.globaltest.model.User;
import com.springboot.users.globaltest.service.IUsersService;
import com.springboot.users.globaltest.validation.IValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private IUsersService iUsersService;

    @Autowired
    private IValidateService iValidateService;

    @GetMapping("/list")
    public ResponseEntity<?> userList() {
        return ResponseEntity.ok().body(iUsersService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            iValidateService.validateCreate(errors, result);
            return ResponseEntity.badRequest().body(errors);
        }
        user.setCreated(new Date());
        user.setLastLogin(new Date());
        user.setModified(new Date());
        user.setActive(true);
        User userDB = iUsersService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDB);
    }

}
