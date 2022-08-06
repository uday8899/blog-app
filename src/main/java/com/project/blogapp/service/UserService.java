package com.project.blogapp.service;


import com.project.blogapp.payload.UserDTO;

import java.util.List;


public interface UserService {

    UserDTO createUser(UserDTO user);

    UserDTO updateUser(UserDTO userDTO,Integer userId);

    UserDTO getUserById(Integer userId);

    List<UserDTO>  getAllUser();

    void deleteUser(Integer userId);
}
