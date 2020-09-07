package com.springboot.users.globaltest.service;

import com.springboot.users.globaltest.model.User;

public interface IJwtTokenService {

    String generateToken(User user);

}
