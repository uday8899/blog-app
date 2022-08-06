package com.project.blogapp.service;
import com.project.blogapp.payload.CategoryDTO;
import java.util.List;

public interface CategoryService {

    //Create
     CategoryDTO createCategory(CategoryDTO categoryDTO);
    //Update
     CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer categoryID);
    //Delete
     void deleteCategory(Integer categoryID);
    //get All
     List<CategoryDTO> getAllCategories();
    //get single category
    CategoryDTO getCategory(Integer categoryID);

}
