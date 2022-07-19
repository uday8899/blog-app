package com.project.blogapp.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CompletePostResponse {

    private List<PostDTO> content;

    private int pageNumber;

    private int pageSize;

    private int totalElements;

    private int totalPages;

    private boolean isLastPage;
}
