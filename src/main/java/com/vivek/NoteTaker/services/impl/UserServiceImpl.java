package com.vivek.NoteTaker.services.impl;

import com.vivek.NoteTaker.dto.UserDTO;
import com.vivek.NoteTaker.entity.User;
import com.vivek.NoteTaker.repository.UserRepository;
import com.vivek.NoteTaker.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @Override
    public User createUser(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }
}
