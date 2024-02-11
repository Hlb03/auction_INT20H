package com.auction.auctions.service;

import com.auction.auctions.entity.User;
import com.auction.auctions.exception.UserAlreadyExistsException;

public interface UserService {

    User addNewUser(User user) throws UserAlreadyExistsException;

    User getUserById(Long id);

    User getUserByEmail(String email);
}
