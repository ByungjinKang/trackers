package org.tracker.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tracker.user.domain.UserDTO;
import org.tracker.user.service.UserService;

import javax.servlet.http.HttpSession;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/index/register")
    public UserDTO register(@RequestBody UserDTO user) {
        return userService.register(user);
    }

}
