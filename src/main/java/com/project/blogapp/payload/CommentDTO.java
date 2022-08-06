package com.project.blogapp.payload;

import com.project.blogapp.entity.Post;
import com.project.blogapp.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {

    private int commentId;

    private String comment;

}
