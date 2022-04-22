package com.ticketmaster.service;
import com.ticketmaster.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
<<<<<<< Updated upstream
import com.ticketmaster.model.Role;

public interface UserService  extends UserDetailsService{
    User register(String username, String password, String name, String surname, Role role);
=======

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
>>>>>>> Stashed changes
}


