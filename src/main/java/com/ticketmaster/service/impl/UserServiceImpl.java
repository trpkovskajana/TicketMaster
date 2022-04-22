package com.ticketmaster.service.impl;

<<<<<<< Updated upstream
import com.ticketmaster.exceptions.InvalidUsernameOrPasswordException;
=======
import com.ticketmaster.exceptions.InvalidUserIdException;
import com.ticketmaster.exceptions.InvalidUsernameOrPasswordException;
import com.ticketmaster.exceptions.PasswordsDoNotMatchException;
>>>>>>> Stashed changes
import com.ticketmaster.exceptions.UsernameAlreadyExistsException;
import com.ticketmaster.model.Role;
import com.ticketmaster.model.User;
import com.ticketmaster.repository.UserRepository;
import com.ticketmaster.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
<<<<<<< Updated upstream
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password,  String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
=======
    public User findByUsername(String username) {
       return this.userRepository.findByUsername(username).orElseThrow(com.ticketmaster.exceptions.UsernameNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(InvalidUserIdException::new);

        UserDetails userDetails = new org.springframework.security.core
                .userdetails.User(user.getUsername(),
                user.getPassword(),
                Stream.of(new SimpleGrantedAuthority(user.getRole().toString())).collect(Collectors.toList()));
        return userDetails;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
>>>>>>> Stashed changes
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }
<<<<<<< Updated upstream


=======
>>>>>>> Stashed changes
}
