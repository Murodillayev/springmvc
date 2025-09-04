package uz.pdp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.model.AuthUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(AuthUser authUser) {
        String query = "INSERT INTO auth_user (id,full_name,role,username, password,role_id) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(query, authUser.getId(), authUser.getFullName(), authUser.getRole(), authUser.getUsername(), authUser.getPassword(), authUser.getRoleId());
    }


    public Optional<AuthUser> findByUsername(String username) {

        String sql = "SELECT * FROM auth_user WHERE username = ?";

        AuthUser user = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> {
                    AuthUser authUser = AuthUser.builder().build();
                    authUser.setUsername(rs.getString("username"));
                    authUser.setPassword(rs.getString("password"));
                    authUser.setId(rs.getString("id"));
                    authUser.setRoleId(rs.getString("role_id"));
                    authUser.setRole(rs.getString("role"));
                    authUser.setFullName(rs.getString("full_name"));
                    return authUser;
                }, username);

        return (user == null) ? Optional.empty() : Optional.of(user);
    }

    public String findRoleByRoleId(String roleId) {

        String sql = "SELECT ar.* FROM auth_role ar WHERE ar.id = ?";

        String roleName = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> rs.getString("code"),
                roleId);
        return roleName;
    }

    public List<String> findAllPermissionsByRoleId(String roleId) {


        String sql = """
                select p.*
                from auth_permission p
                         left join auth_role_permission rp on rp.permission_id = p.id
                where rp.role_id = ?
                """;

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> rs.getString("code"),
                roleId);
    }


}
