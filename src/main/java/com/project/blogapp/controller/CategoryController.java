package com.project.blogapp.controller;

import com.project.blogapp.payload.ApiResponse;
import com.project.blogapp.payload.CategoryDTO;
import com.project.blogapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //Create API
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){

        CategoryDTO createdCategory = this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    //Update API
    @PutMapping("/{categoryID}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                      @PathVariable("categoryID") Integer categoryID){

        CategoryDTO updatedCategory = this.categoryService.updateCategory(categoryDTO,categoryID);

        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

    //Delete API
    @DeleteMapping("/{categoryID}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryID){

        this.categoryService.deleteCategory(categoryID);

        return  new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }

    //Find All Categories REST API
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categoriesToDisplay = this.categoryService.getAllCategories();

        return new ResponseEntity<>(categoriesToDisplay,HttpStatus.OK);
    }

    //Find Single Category REST API
    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryID){

        CategoryDTO categoryToDisplay = this.categoryService.getCategory(categoryID);

        return new ResponseEntity<>(categoryToDisplay,HttpStatus.OK);
    }
}
