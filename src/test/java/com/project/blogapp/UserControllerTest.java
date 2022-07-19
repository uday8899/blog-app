package com.project.blogapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.project.blogapp.controller.UserController;
import com.project.blogapp.entity.Category;
import com.project.blogapp.entity.Post;
import com.project.blogapp.entity.User;
import com.project.blogapp.service.UserService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

//    Post pos1 = new Post(1,"Title1","ContentSample1","img1",new Date(),new Date(),
//            new Category(1,"Sample Category 1","Description 1",))
//    User user1 = new User(1,"Sample User 1","password1",);

}
