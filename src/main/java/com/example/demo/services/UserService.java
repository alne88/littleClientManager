package com.example.demo.services;

import com.example.demo.models.UserDto;
import com.example.demo.persistence.ClientsDao;
import com.example.demo.persistence.UsersDao;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
public class UserService implements UserDetailsService {

    private final UsersDao usersDao;
    private final ClientsDao clientsDao;

    public UserService(UsersDao usersDao, ClientsDao clientsDao) {
        this.usersDao = usersDao;
        this.clientsDao = clientsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String errorMessage = format("Username %s not found", username);
        UserDto user = ofNullable(findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException(errorMessage));

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        grantList.add(new SimpleGrantedAuthority(user.getRole().name()));

        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), grantList);
        return userDetails;
    }

    public UserDto findByUsername(String username) {
        return usersDao.findByUsername(username);
    }

    public Long getLoggedInUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersDao.findByUsername(username).getId();
    }
}
