package com.project.blogapp.controller;

import com.project.blogapp.payload.ApiResponse;
import com.project.blogapp.payload.CommentDTO;
import com.project.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/user/{userId}/post/{postId}")
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody CommentDTO commentDTO, @PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId){

        CommentDTO commentToCreate = this.commentService.createComment(commentDTO,userId,postId);

        return new ResponseEntity<>(commentToCreate,HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CommentDTO>> getComments(){

        List<CommentDTO> fetchedComments = this.commentService.getAllComments();

        return new ResponseEntity<>(fetchedComments,HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO,@PathVariable("commentId") Integer commentId){
        CommentDTO commentToUpdate = this.commentService.updateComment(commentDTO,commentId);
        return new ResponseEntity<>(commentToUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
          this.commentService.deleteComment(commentId);
          return new ResponseEntity<>(new ApiResponse("Delete Operation Successful",true),HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Integer commentId){
        CommentDTO fetchedComment = this.commentService.getComment(commentId);
        return new ResponseEntity<>(fetchedComment,HttpStatus.OK);
    }
}
