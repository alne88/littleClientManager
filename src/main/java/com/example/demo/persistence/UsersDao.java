package com.example.demo.persistence;

import com.example.demo.models.UserDto;

public interface UsersDao {

    UserDto findByUsername(String username);

}
