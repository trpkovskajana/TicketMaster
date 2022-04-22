package com.ticketmaster.service.impl;


import com.ticketmaster.exceptions.InvalidArgumentsException;
import com.ticketmaster.exceptions.InvalidUserCredentialsException;
import com.ticketmaster.model.User;
import com.ticketmaster.repository.UserRepository;
import com.ticketmaster.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
