package com.auction.auctions.service.impl;

import com.auction.auctions.entity.User;
import com.auction.auctions.exception.UserAlreadyExistsException;
import com.auction.auctions.repository.UserRepository;
import com.auction.auctions.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User addNewUser(User user) throws UserAlreadyExistsException {
        log.info("User info gained from user is: {}", user);
        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            log.info("Login {} is already present", user.getEmail());
            throw new UserAlreadyExistsException(String.format("Login %s is already taken, please choose another one", user.getEmail()));
        }

        user.setActivityRate(0);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        log.info("Gaining info about user with id {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Finding user by email {}", email);
        return userRepository.getUserByEmail(email);
    }
}
