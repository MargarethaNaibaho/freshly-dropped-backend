package com.wesclic.freshlydropped.service.impl;

import com.wesclic.freshlydropped.entity.RecipeImage;
import com.wesclic.freshlydropped.repository.RecipeImageRepository;
import com.wesclic.freshlydropped.service.RecipeImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class RecipeImageServiceImpl implements RecipeImageService {
    private final RecipeImageRepository recipeImageRepository;

    private final Path thumbnailImagePath = Paths.get("./asset/image/thumbnail");
    private final Path detailImagePath = Paths.get("./asset/image/detail");

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecipeImage createThumbnailImage(MultipartFile multipartFile) {
        return saveImage(multipartFile, thumbnailImagePath);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecipeImage createDetailImage(MultipartFile multipartFile) {
        return saveImage(multipartFile, detailImagePath);
    }

    private RecipeImage saveImage(MultipartFile multipartFile, Path directoryPath){
        if(multipartFile.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File isn't found");

        try{
            Files.createDirectories(directoryPath);
            String filename = String.format("%d-%s", System.currentTimeMillis(), multipartFile.getOriginalFilename());
            Path filePath = directoryPath.resolve(filename);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            RecipeImage recipeImage = RecipeImage.builder()
                    .name(filename)
                    .contentType(multipartFile.getContentType())
                    .path(filePath.toString())
                    .size(multipartFile.getSize())
                    .build();

            return recipeImageRepository.saveAndFlush(recipeImage);
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving file");
        }
    }

    @Override
    public Resource findByPath(String path) {
        try {
            Path filePath = Paths.get(path);
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
