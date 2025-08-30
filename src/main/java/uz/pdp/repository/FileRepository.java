package uz.pdp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.model.FileInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class FileRepository {

    private final JdbcTemplate jdbcTemplate;

    public FileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(FileInfo fileInfo) {
        String sql = "insert into file_info(id, name, original_name, size, content_type, path) values(?,?,?,?,?,?)";

        jdbcTemplate.update(sql,fileInfo.getId(), fileInfo.getName(), fileInfo.getOriginalName(), fileInfo.getSize(), fileInfo.getContentType(), fileInfo.getPath());
    }

    public Optional<FileInfo> findByName(String fileName) {
        String sql = "SELECT * FROM file_info WHERE name = ?";

        FileInfo fileInfo = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            FileInfo fileInfo1 = new FileInfo();
            fileInfo1.setName(rs.getString("name"));
            fileInfo1.setPath(rs.getString("path"));
            fileInfo1.setSize(rs.getLong("size"));
            fileInfo1.setOriginalName(rs.getString("original_name"));
            fileInfo1.setContentType(rs.getString("content_type"));
            return fileInfo1;
        }, fileName);

        return Optional.ofNullable(fileInfo);

    }
}
