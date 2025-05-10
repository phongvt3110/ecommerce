package com.org.shopping.controllers;

import com.org.shopping.exceptions.FileProcessingError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Controller
@RequestMapping("/ex-images")
public class ImageController {
    @Value("${files.upload.dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam(value = "path", defaultValue = "") String path, @RequestParam("file") MultipartFile file) {
        path = path.replaceAll("^/+|/+$", "");
        Path uploadPath = Paths.get(uploadDir).resolve(path).normalize();
        if(!uploadPath.startsWith(Paths.get(uploadDir))) {
            throw new SecurityException("Truy cap khong hop le");
        }
        try {
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            String fileUrl = "/ex-images/" + (path.isEmpty() ? "" : path + "/")  + fileName;
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            throw new FileProcessingError("Loi khi thao tac file");
        }
    }

    @GetMapping("/**")
    public ResponseEntity<Resource> getImage(HttpServletRequest request) {
        String uri = request.getRequestURI().replaceFirst("^/ex-images/", "");
        Path path = Paths.get(uploadDir).resolve(uri).normalize();
        if(!path.startsWith(Paths.get(uploadDir))) {
            throw new SecurityException("Truy cap khong hop le");
        }
        try {
            Resource resource = new UrlResource(path.toUri());

            if(resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(path);
                if(contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                throw new FileNotFoundException("Can not found the image: " + path.toString());
            }
        } catch (IOException e) {
            throw new FileProcessingError("File processing error: " + e.getMessage() );
        }
    }
}
