package com.project.blogapp.service.Impl;

import com.project.blogapp.entity.User;
import com.project.blogapp.exception.ResourceNotFoundException;
import com.project.blogapp.payload.UserDTO;
import com.project.blogapp.repository.UserRepository;
import com.project.blogapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    //1. Rest API to Create a New User
    @Override
    public UserDTO createUser(UserDTO user) {
        User userToCreate = this.dtoToUser(user);
        User savedUser = this.userRepository.save(userToCreate);
        return userToDTO(savedUser);
    }

    //2.Rest API to Update an Existing User
    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User userToUpdate = this.userRepository.findById(userId)
                .orElseThrow( ()-> new ResourceNotFoundException("User","id",userId));

        userToUpdate.setName(userDTO.getName());
        userToUpdate.setPassword(userDTO.getPassword());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setAbout(userDTO.getAbout());

        this.userRepository.save(userToUpdate);

        return this.userToDTO(userToUpdate);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User userToFind = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
        return this.userToDTO(userToFind);
    }

    @Override
    public List<UserDTO> getAllUser() {

        List<User> usersFound = this.userRepository.findAll();

        List<UserDTO> usersToDisplay = usersFound.stream().map(this::userToDTO).toList();
        return usersToDisplay;
    }

    @Override
    public void deleteUser(Integer userId) {
        User userToDelete = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));

        this.userRepository.delete(userToDelete);
    }

    public User dtoToUser(UserDTO userDTO){
        User user = this.modelMapper.map(userDTO,User.class);
//        user.setId(userDTO.getId());
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        user.setAbout(userDTO.getAbout());

        return user;
    }
    public UserDTO userToDTO(User user){
        UserDTO userDTO = this.modelMapper.map(user,UserDTO.class);
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());

        return userDTO;
    }
}
