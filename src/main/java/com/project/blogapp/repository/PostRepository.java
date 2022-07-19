package com.project.blogapp.repository;

import com.project.blogapp.entity.Category;
import com.project.blogapp.entity.Post;
import com.project.blogapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    //API- Get All the Posts of One Particular User
    Page<Post>  findAllByUser(User user,Pageable pageable);

    //API- Get All the Posts of One Particular Category
    Page<Post> findAllByCategory(Category category, Pageable pageable);

    //Search Post By Title

    List<Post> findByPostTitleContaining(String title);

}
