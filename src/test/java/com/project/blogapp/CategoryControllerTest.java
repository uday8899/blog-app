package com.project.blogapp;

import com.project.blogapp.controller.CategoryController;
import com.project.blogapp.entity.Category;
import com.project.blogapp.entity.Post;
import com.project.blogapp.service.CategoryService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;
    Post post1 = new Post(1,"Sample Title 1","Sample Content 1",)
    Category category = new Category(1,"Sample Category 1","Sample Description 1",)


}
