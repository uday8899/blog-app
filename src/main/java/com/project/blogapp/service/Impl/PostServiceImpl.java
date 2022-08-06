package com.project.blogapp.service.Impl;

import com.project.blogapp.entity.Category;
import com.project.blogapp.entity.Post;
import com.project.blogapp.entity.User;
import com.project.blogapp.exception.ResourceNotFoundException;
import com.project.blogapp.service.PostService;
import com.project.blogapp.payload.CompletePostResponse;
import com.project.blogapp.payload.PostDTO;
import com.project.blogapp.repository.CategoryRepository;
import com.project.blogapp.repository.PostRepository;
import com.project.blogapp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;


    //Create
    @Override
    public PostDTO createPost(PostDTO postDTO,Integer userID, Integer categoryID) {

        User user = this.userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User","User ID",userID));
        Category category = this.categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryID));

        Post postToCreate = this.dtoToPost(postDTO);

        postToCreate.setCategory(category);
        postToCreate.setUser(user);
        Post createPost = this.postRepository.save(postToCreate);

        return this.postToDTO(createPost);

    }

    //Update
    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post postToUpdate = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post"
                                                  , "Post ID",postId));
        postToUpdate.setPostImage(postDTO.getPostImage());
        postToUpdate.setPostTitle(postDTO.getPostTitle());
        postToUpdate.setPostContent(postDTO.getPostContent());
        this.postRepository.save(postToUpdate);
        PostDTO updatePost = this.postToDTO(postToUpdate);
        return updatePost;
    }
    //Delete Post By ID
    @Override
    public void deletePost(Integer postId) {

        Post postToBeDeleted = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post,","Post ID",postId));

        this.postRepository.delete(postToBeDeleted);
    }

    //Get the List of All the posts
    @Override
    public CompletePostResponse getPosts(Integer pageNumber, Integer pageSize,String sortBy) {

        Pageable paginationImpl = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        Page<Post> postsByPage = this.postRepository.findAll(paginationImpl);
        List<Post> postsToDisplay = postsByPage.getContent();

        List<PostDTO> displayedPosts = postsToDisplay.stream().map(this::postToDTO).toList();

        CompletePostResponse postData = new CompletePostResponse();
        postData.setContent(displayedPosts);
        postData.setPageNumber(postsByPage.getNumber());
        postData.setPageSize(postsByPage.getSize());
        postData.setTotalElements((int)postsByPage.getTotalElements());
        postData.setLastPage(postsByPage.isLast());
        postData.setTotalPages(postsByPage.getTotalPages());
        return postData;
    }

    //Get Posts By post ID
    @Override
    public PostDTO getPost(Integer postId) {
        Post postById = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Post ID",postId));

        return this.postToDTO(postById);
    }
    //Get Post By CategoryId
    @Override
    public CompletePostResponse getPostByCategory(Integer categoryID,Integer pageNumber,Integer pageSize,String sortBy) {

        Category  categoryCheck = this.categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryID));

        Pageable pagination = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
        Page<Post> page = this.postRepository.findAllByCategory(categoryCheck,pagination);
        List<Post> postsToDisplayByCategory = page.getContent();

        List<PostDTO> displayedPostsByCategory = postsToDisplayByCategory.stream().map(this::postToDTO).toList();

        CompletePostResponse response = new CompletePostResponse();
        response.setContent(displayedPostsByCategory);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements((int) page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }
    //Get All the posts by user id
    @Override
    public CompletePostResponse getPostByUser(Integer userID,Integer pageNumber,Integer pageSize,String sortBy) {
        User checkForUser = this.userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User","User ID",userID));

        Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
        Page<Post> page =  this.postRepository.findAllByUser(checkForUser,pageable);
        List<Post> postByUserID = page.getContent();

        List<PostDTO> postsByUserID = postByUserID.stream().map(this::postToDTO).toList();

        CompletePostResponse response = new CompletePostResponse();
        response.setContent(postsByUserID);
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements((int)page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLastPage(page.isLast());
        return response;
    }

    @Override
    public List<PostDTO> getPostsByKeyword(String keyword) {

        List<Post> searchPostsByTitle = this.postRepository.findByPostTitleContaining(keyword);
        List<PostDTO> searchedPosts = searchPostsByTitle.stream()
                .map(this::postToDTO).toList();

        return searchedPosts;
    }


     /*Model Mapper Methods*/

    public Post dtoToPost(PostDTO postDTO){
        return this.modelMapper.map(postDTO,Post.class);
    }

    public PostDTO postToDTO(Post post){
        return this.modelMapper.map(post,PostDTO.class);
    }

}
