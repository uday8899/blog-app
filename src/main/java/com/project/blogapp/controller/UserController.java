package com.project.blogapp.controller;

import com.project.blogapp.payload.ApiResponse;
import com.project.blogapp.payload.UserDTO;
import com.project.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    //1.Rest API to Create a New User

    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
        UserDTO createUserDTO = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }

    //2.Rest API To Update The User

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable Integer userId){

        UserDTO updateUserDTO = this.userService.updateUser(userDTO,userId);

        return ResponseEntity.ok(userDTO);
    }

    //3.Rest API To Delete The User

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteTheUser(@PathVariable("userId") Integer uId){
        this.userService.deleteUser(uId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successful",true),HttpStatus.OK);
    }

    //4. Rest API To Get All The Users

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        List<UserDTO> getAllUsersDTO = this.userService.getAllUser();
        return new ResponseEntity<>(getAllUsersDTO,HttpStatus.OK);
    }

    //5.Rest API To Get Single User

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer userId){

        UserDTO getUserDTO = this.userService.getUserById(userId);

        return new ResponseEntity<>(getUserDTO,HttpStatus.OK);
    }
}
