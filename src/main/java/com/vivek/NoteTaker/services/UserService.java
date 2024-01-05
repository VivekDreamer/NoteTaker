package com.vivek.NoteTaker.services;

import com.vivek.NoteTaker.dto.UserDTO;
import com.vivek.NoteTaker.entity.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User createUser(UserDTO userDTO);
}
