package com.ssafy.spot.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

//  private final Path root = Paths.get("C:/Users/multicampus/Desktop");
  private final Path root = Paths.get("/home/ubuntu/");

  @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void saveimage(MultipartFile file, String timeurl) {
    try {
    	System.out.println(this.root.resolve(file.getOriginalFilename())); //.\HBD.jpg
      Files.copy(file.getInputStream(), this.root.resolve("profile/"+timeurl+file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }
  
}