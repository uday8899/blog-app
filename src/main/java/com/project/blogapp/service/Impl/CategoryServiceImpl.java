package com.project.blogapp.service.Impl;

import com.project.blogapp.entity.Category;
import com.project.blogapp.exception.ResourceNotFoundException;
import com.project.blogapp.payload.CategoryDTO;
import com.project.blogapp.repository.CategoryRepository;
import com.project.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    //Create API
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category categoryToCreate = this.dtoToCategory(categoryDTO);
        Category savedCategory = this.categoryRepository.save(categoryToCreate);
        return this.categoryToDTO(savedCategory);
    }

    //Update API
    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryID) {

        Category categoryToUpdate = this.categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category","category Id",categoryID));


        categoryToUpdate.setTitle(categoryDTO.getTitle());
        categoryToUpdate.setDescription(categoryDTO.getDescription());

        this.categoryRepository.save(categoryToUpdate);

        return this.categoryToDTO(categoryToUpdate);
    }

    //Delete API
    @Override
    public void deleteCategory(Integer categoryID) {

        Category categoryToDelete = this.categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryID));

        this.categoryRepository.delete(categoryToDelete);
    }

    //Find All Categories API
    @Override
    public List<CategoryDTO> getAllCategories() {

        List<CategoryDTO> categoriesFound = this.categoryRepository.findAll()
                .stream().map(this::categoryToDTO).toList();

        return categoriesFound;
    }

    //Find By A Single Category
    @Override
    public CategoryDTO getCategory(Integer categoryID) {

        Category categoryToDisplay = this.categoryRepository.findById(categoryID)
                .orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryID));

        return this.categoryToDTO(categoryToDisplay);
    }



    public Category dtoToCategory(CategoryDTO categoryDTO){
        return this.modelMapper.map(categoryDTO,Category.class);
    }
    public CategoryDTO categoryToDTO(Category category){
        return this.modelMapper.map(category,CategoryDTO.class);
    }

}
