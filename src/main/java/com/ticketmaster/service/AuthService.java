package com.ticketmaster.service;

import com.ticketmaster.model.User;

public interface AuthService {
    User login(String username, String password);
}
