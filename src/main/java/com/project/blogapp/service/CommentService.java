package com.project.blogapp.service;

import com.project.blogapp.payload.CommentDTO;

import java.util.List;

public interface CommentService {

    //1.Get All the Comments
    List<CommentDTO> getAllComments();
    //2.Get Comments By comment ID
    CommentDTO getComment(Integer commentID);
    //3.Create Comment
    CommentDTO createComment(CommentDTO comment,Integer userID,Integer postID);
    //4.Update Comment
    CommentDTO updateComment(CommentDTO comment,Integer commentID);
    //5. Delete Comment
    void deleteComment(Integer commentID);

}
