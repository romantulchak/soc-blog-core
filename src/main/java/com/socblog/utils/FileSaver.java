package com.socblog.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class FileSaver {


    public static String saveFile(MultipartFile file, String fullUploadPath, String pathToReturn) throws IOException {
        if(file != null){
            String fileOriginalName = UUID.randomUUID() + "." + file.getOriginalFilename();
            String filePath = fullUploadPath + "/" + fileOriginalName;
            Path copyLocation = Paths.get(filePath);
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            return "http://localhost:8080/" + pathToReturn + "/" + fileOriginalName;
        }
        return null;
    }

}
