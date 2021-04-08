package com.ssafy.spot.service;

import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
  public void init();

  public void saveimage(MultipartFile file, String timeurl);
}