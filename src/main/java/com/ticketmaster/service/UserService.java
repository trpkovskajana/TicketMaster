package com.ticketmaster.service;
import com.ticketmaster.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.ticketmaster.model.Role;

public interface UserService  extends UserDetailsService{
    User register(String username, String password, String name, String surname, Role role);
}


