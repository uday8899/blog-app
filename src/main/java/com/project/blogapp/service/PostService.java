package com.project.blogapp.service;

import com.project.blogapp.payload.CompletePostResponse;
import com.project.blogapp.payload.PostDTO;

import java.util.List;

public interface PostService {

    //Create Post

    PostDTO createPost(PostDTO postDTO, Integer userID, Integer categoryID);

    //Update

    PostDTO updatePost(PostDTO postDTO,Integer postId);

    //Delete
    void deletePost(Integer postId);

    //Find All

    CompletePostResponse getPosts(Integer pageNumber, Integer pageSize,String sortBy);

    //Find By Id

    PostDTO getPost(Integer postId);

    //Find post By category

    CompletePostResponse getPostByCategory(Integer categoryID,Integer pageNumber,Integer pageSize,String sortBy);

    //Find Post By User

    CompletePostResponse getPostByUser(Integer userID,Integer pageNumber,Integer pageSize,String sortBy);

    //Search Posts By KeyWord

    List<PostDTO>  getPostsByKeyword(String keyword);
}
