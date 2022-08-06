package com.project.blogapp.service.Impl;

import com.project.blogapp.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String originalFileName = file.getOriginalFilename();
        String uniqueFileID = UUID.randomUUID().toString();

        String uniqueFileName =uniqueFileID.concat(originalFileName.substring(originalFileName.lastIndexOf('.')));

        String filePath = path + File.separator + uniqueFileName;

        File fileStorage = new File(path);

        if(!fileStorage.exists()){
            fileStorage.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return originalFileName;
    }

    @Override
    public InputStream serveImage(String path, String fileName) throws FileNotFoundException {

        String filePath = path + File.separator + fileName;

        InputStream fileResponseBytes =new FileInputStream(filePath);

        return fileResponseBytes;
    }


}
