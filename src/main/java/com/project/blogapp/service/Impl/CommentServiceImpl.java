package com.project.blogapp.service.Impl;

import com.project.blogapp.entity.Comment;
import com.project.blogapp.entity.Post;
import com.project.blogapp.entity.User;
import com.project.blogapp.exception.ResourceNotFoundException;
import com.project.blogapp.payload.CommentDTO;
import com.project.blogapp.repository.CommentRepository;
import com.project.blogapp.repository.PostRepository;
import com.project.blogapp.repository.UserRepository;
import com.project.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CommentDTO> getAllComments() {
        List<Comment> commentsFetchedFromDB = this.commentRepository.findAll();
        List<CommentDTO> commentsToDisplay = commentsFetchedFromDB.stream()
                .map(this::commentToDTO).toList();
        return commentsToDisplay;
    }

    @Override
    public CommentDTO getComment(Integer commentID) {
        Comment commentFetchedFromDB = this.commentRepository.findById(commentID)
                .orElseThrow(() -> new ResourceNotFoundException("Comment","Comment ID",commentID));
        CommentDTO commentToDisplay = this.commentToDTO(commentFetchedFromDB);
        return commentToDisplay;
    }

    @Override
    public CommentDTO createComment(CommentDTO comment,Integer userID,Integer postID) {

        User user = this.userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User","User ID",userID));
        Post post = this.postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("Post","Post ID",postID));
        Comment commentToCreate = this.dtoToComment(comment);
        commentToCreate.setUser(user);
        commentToCreate.setPost(post);
        Comment commentCreated = this.commentRepository.save(commentToCreate);
        return this.commentToDTO(commentCreated);
    }

    @Override
    public CommentDTO updateComment(CommentDTO comment,Integer commentID) {
        Comment commentFetchedFromDB = this.commentRepository.findById(commentID)
                .orElseThrow(() -> new ResourceNotFoundException("Comment","Comment ID",commentID));
        commentFetchedFromDB.setComment(comment.getComment());

        Comment updatedComment = this.commentRepository.save(commentFetchedFromDB);
        return this.commentToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Integer commentID) {
        this.commentRepository.deleteById(commentID);
    }


    /*Mode Mapper Methods*/
    private Comment dtoToComment(CommentDTO commentDTO){
        return this.modelMapper.map(commentDTO,Comment.class);
    }
    private CommentDTO commentToDTO(Comment comment){
        return this.modelMapper.map(comment,CommentDTO.class);
    }
}
