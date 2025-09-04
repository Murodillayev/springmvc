package uz.pdp.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.AuthUser;

import java.util.Optional;

@Repository
public class AuthUserRepository {

    private final JdbcTemplate jdbcTemplate;

    public AuthUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(AuthUser authUser) {
        String query = "INSERT INTO auth_user (id,full_name,role,username, password) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(query, authUser.getId(), authUser.getFullName(), authUser.getRole(), authUser.getUsername(), authUser.getPassword());
    }


    public Optional<AuthUser> findByUsername(String username) {

        String sql = "SELECT * FROM auth_user WHERE username = ?";

        AuthUser user = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) -> {
                    AuthUser authUser = AuthUser.builder().build();
                    authUser.setUsername(rs.getString("username"));
                    authUser.setPassword(rs.getString("password"));
                    authUser.setId(rs.getString("id"));
                    authUser.setRole(rs.getString("role"));
                    authUser.setFullName(rs.getString("full_name"));
                    return authUser;
                }, username);

        return (user == null) ? Optional.empty() : Optional.of(user);
    }
}
