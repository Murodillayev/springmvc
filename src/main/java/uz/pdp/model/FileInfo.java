package uz.pdp.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String originalName;
    private String path;
    private String contentType;
    private Long size;
}
