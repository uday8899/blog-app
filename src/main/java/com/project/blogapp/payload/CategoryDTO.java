package com.project.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

    private int categoryId;

    @NotEmpty
    @Size(min = 2,max = 100)
    private String title;

    @NotEmpty
    @Size(max = 5000)
    private String description;

}
