package uz.pdp.controller;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.model.FileDto;
import uz.pdp.model.FileInfo;
import uz.pdp.service.FileService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }


    @RequestMapping(value = "/upload")
    public String uploadFilePage() {
        return "uploads";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(name = "file") MultipartFile[] files) {

        for (MultipartFile file : files) {
            service.upload(file);
        }
        return "uploads";
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName) {
        FileInfo fileInfo = service.download(fileName);
        if (fileInfo == null) {
            return ResponseEntity.notFound().build();
        }
        FileSystemResource resource = new FileSystemResource(fileInfo.getPath());
        return ResponseEntity.ok()
                .contentLength(fileInfo.getSize())
                .contentType(fileInfo.getContentType() == null ? MediaType.APPLICATION_OCTET_STREAM : MediaType.parseMediaType(fileInfo.getContentType()))
                .header("Content-Disposition", "attachment; filename=\"" + fileInfo.getOriginalName() + "\"")
                .body(resource);
    }

    @GetMapping("/download2/{fileName}")
    public void download2(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        FileInfo fileInfo = service.download(fileName);
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getOriginalName() + "\"");
        response.addHeader("Content-Type", fileInfo.getContentType());


        try (FileInputStream fileInputStream = new FileInputStream(fileInfo.getPath());
             OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


