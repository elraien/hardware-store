package io.hardware.store.api.controller;

import io.hardware.store.api.model.user.User;
import io.hardware.store.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    User findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }
}
