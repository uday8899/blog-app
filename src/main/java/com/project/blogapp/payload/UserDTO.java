package com.project.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be of minimum 4 characters")
    private String name;

    @Email(message = "Email Address is Not Valid")
    private String email;

    @NotEmpty
    @Size(min = 3,max = 10,message = "password must be between 3 and 10 characters")
    private String password;

    @NotEmpty
    private  String about;

    private List<CommentDTO> comments;

}
