package com.wesclic.freshlydropped.service;

import com.wesclic.freshlydropped.entity.RecipeImage;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface RecipeImageService {
    RecipeImage createThumbnailImage(MultipartFile multipartFile);
    RecipeImage createDetailImage(MultipartFile multipartFile);
    Resource findByPath(String path);
}
