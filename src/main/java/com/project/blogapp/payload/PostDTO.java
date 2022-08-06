package com.project.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {

    private Integer postId;

    @NotEmpty
    private String postTitle;
    @NotEmpty
    private String postContent;
    @NotEmpty
    private String postImage;

    private UserDTO user;
    private CategoryDTO category;

    private List<CommentDTO> comments;

}
