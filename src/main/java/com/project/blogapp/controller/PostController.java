package com.project.blogapp.controller;

import com.project.blogapp.config.ConstantValues;
import com.project.blogapp.payload.ApiResponse;
import com.project.blogapp.payload.CompletePostResponse;
import com.project.blogapp.payload.PostDTO;
import com.project.blogapp.service.FileService;
import com.project.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${file.path}")
    private String path;

    //Create a New Post
    @PostMapping("/userId/{userId}/categoryId/{categoryId}")
    public ResponseEntity<PostDTO> postToCreate(@RequestBody PostDTO postDTO,
                                                @PathVariable Integer userId,@PathVariable Integer categoryId){
       PostDTO createdPost =  this.postService.createPost(postDTO,userId,categoryId);

       return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    //List all the posts
    @GetMapping("/")
    public ResponseEntity<CompletePostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = ConstantValues.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = ConstantValues.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = ConstantValues.SORT_BY,required = false) String sortBy
    ){
        CompletePostResponse completePostResponse = this.postService.getPosts(pageNumber,pageSize,sortBy);

        return new ResponseEntity<>(completePostResponse,HttpStatus.OK);
    }

    //List Post By Post Id
    @GetMapping("/{postID}")
    public ResponseEntity<PostDTO> getPostByID(@PathVariable Integer postID){
        PostDTO postToDisplay = this.postService.getPost(postID);

        return new ResponseEntity<>(postToDisplay,HttpStatus.OK);
    }
    //List Post By Category ID
    @GetMapping("/category/{categoryID}")
    public ResponseEntity<CompletePostResponse> getPostsByCategory(@PathVariable Integer categoryID,
                                                            @RequestParam(value = "pageNumber",defaultValue = ConstantValues.PAGE_NUMBER,required = false)
                                                            Integer pageNumber,
                                                            @RequestParam(value = "pageSize",defaultValue = ConstantValues.PAGE_SIZE,required = false)
                                                            Integer pageSize,
                                                            @RequestParam(value = "sortBy",defaultValue = ConstantValues.SORT_BY,required = false)
                                                            String sortBy){

        CompletePostResponse postsToDisplayByCategoryID = this.postService.getPostByCategory(categoryID,pageNumber,pageSize,sortBy);

        return new ResponseEntity<>(postsToDisplayByCategoryID,HttpStatus.OK);
    }
    //Get All the posts by user id
    @GetMapping("/user/{userID}")
    public ResponseEntity<CompletePostResponse> getPostsByUsers(@PathVariable Integer userID,
                                                         @RequestParam(value = "pageNumber",defaultValue = ConstantValues.PAGE_NUMBER,required = false)
                                                         Integer pageNumber,
                                                         @RequestParam(value = "pageSize",defaultValue = ConstantValues.PAGE_SIZE,required = false)
                                                         Integer pageSize,
                                                         @RequestParam(value = "sortBy",defaultValue = ConstantValues.SORT_BY,required = false)
                                                         String sortBy){

        CompletePostResponse postsToDisplayByUserID = this.postService.getPostByUser(userID,pageNumber,pageSize,sortBy);

        return new ResponseEntity<>(postsToDisplayByUserID,HttpStatus.OK);

    }
    //Delete Posts By Id
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse> deletePostByID(@PathVariable Integer postId){
        this.postService.deletePost(postId);

        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
    }

    //Update Post
    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Integer postId){

        PostDTO postToUpdate = this.postService.updatePost(postDTO,postId);

        return new ResponseEntity<>(postToUpdate,HttpStatus.OK);
    }
    //Search Posts By Post Title
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPostsByTitle(@PathVariable String keyword){

        List<PostDTO> searchPostsByTitle = this.postService.getPostsByKeyword(keyword);

        return new ResponseEntity<>(searchPostsByTitle,HttpStatus.OK);

    }

    //Upload File in the DataBase

    @PostMapping("/file/{postID}")
    public ResponseEntity<PostDTO> uploadFile(
            @RequestParam(value = "image") MultipartFile file,
            @PathVariable("postID") Integer postID
            ) throws IOException {
        String fileName = this.fileService.uploadImage(this.path,file);

        PostDTO getPostById = this.postService.getPost(postID);

        getPostById.setPostImage(fileName);

        PostDTO updatedPost = this.postService.updatePost(getPostById,postID);

        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }
    //Display The Image in the Screen
    @GetMapping(value = "/file/{fileName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ApiResponse> serveImage(
            @PathVariable("fileName") String fileName,
            HttpServletResponse response
    ) throws IOException {
        InputStream fileResponseBytes = this.fileService.serveImage(this.path,fileName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(fileResponseBytes,response.getOutputStream());

        return new ResponseEntity<>(new ApiResponse("File Displayed",true),HttpStatus.OK);
    }

}
