package com.springboot.users.globaltest.service;

import com.springboot.users.globaltest.model.User;
import java.util.Optional;

public interface IUsersService {

    Iterable<User> findAll();

    User save(User user);

    Optional<User> findUserByEmail(String email);
}
