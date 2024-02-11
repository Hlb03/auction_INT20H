package com.auction.auctions.controller;

import com.auction.auctions.entity.User;
import com.auction.auctions.exception.UserAlreadyExistsException;
import com.auction.auctions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User obtainUserViaId(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public User obtainUserViaEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/add/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody User user) {
        try {
            return userService.addNewUser(user);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
