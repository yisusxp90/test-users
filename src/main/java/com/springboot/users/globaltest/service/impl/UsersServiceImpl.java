package com.springboot.users.globaltest.service.impl;


import com.springboot.users.globaltest.model.User;
import com.springboot.users.globaltest.repository.IUsersRepository;
import com.springboot.users.globaltest.service.IJwtTokenService;
import com.springboot.users.globaltest.service.IUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsersServiceImpl implements IUsersService {

    private final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);

    @Autowired
    private IUsersRepository iUsersRepository;
    @Autowired
    private IJwtTokenService jwtToken;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() {
        logger.info("------------Getting all users---------------");
        return iUsersRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        logger.info("------------Save user---------------");
        final String token = jwtToken.generateToken(user);
        user.setToken(token);
        if(user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        logger.info("------------Finish Save user--------");
        return iUsersRepository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return iUsersRepository.findUserByEmail(email);
    }
}
