package com.project.blogapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

  //Upload the File into the DATABASE
   String uploadImage(String path,MultipartFile file) throws IOException;
   //Retreive the IMAGE from the database and display
    InputStream serveImage(String path, String fileName) throws FileNotFoundException;
}
