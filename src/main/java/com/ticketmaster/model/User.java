package com.ticketmaster.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
<<<<<<< Updated upstream
import java.util.List;
import java.util.stream.Collectors;
=======
>>>>>>> Stashed changes

@Data
@Entity
@Table(name = "shop_users")
public class User implements UserDetails {

    @Id
    private String username;

    private String password;

    private String name;

    private String surname;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;


    @Enumerated(value = EnumType.STRING)
    private Role role;


<<<<<<< Updated upstream
   /* @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;
*/
    public User() {
    }

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }
=======
    /* @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
     private List<ShoppingCart> carts;*/

    public User() {
    }
    /*
       public User(String username, String password,  Role role) {
           this.username = username;
           this.password = password;
           this.role = rol       }
      */
       public User(String username, String password, String name, String surname, Role role) {
           this.username = username;
           this.password = password;
           this.name = name;
           this.surname = surname;
           this.role = role;
       }
>>>>>>> Stashed changes

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
