package com.project.blogapp.repository;

import com.project.blogapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
