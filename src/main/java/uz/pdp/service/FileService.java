package uz.pdp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.model.FileInfo;
import uz.pdp.repository.FileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository repository;

    private final String root = "/Users/macbookpro/Documents/pdp/springmvcdemo/src/main/resources/store";

    public FileService(FileRepository repository) {
        this.repository = repository;
    }

    public void upload(MultipartFile file) {
        try {
            String genName = UUID.randomUUID().toString().replace("-", "") + getFileExtension(file.getOriginalFilename());
            Path filePath = Paths.get(root, genName);
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            Files.write(filePath, file.getBytes());

            FileInfo fileInfo = new FileInfo();
            fileInfo.setName(genName);
            fileInfo.setSize(file.getSize());
            fileInfo.setPath(filePath.toString());
            fileInfo.setContentType(file.getContentType());
            fileInfo.setOriginalName(file.getOriginalFilename());
            repository.save(fileInfo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public FileInfo download(String fileName) {
        return repository.findByName(fileName).orElse(null);
    }

    private String getFileExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex >= 0) {
            return filename.substring(dotIndex);
        }
        return "";
    }
}
