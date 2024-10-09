package tdh.quanlytoanha.security.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tdh.quanlytoanha.security.entities.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username); // Thêm phương thức này
    boolean existsByEmail(String email); // Thêm phương thức kiểm tra email
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u JOIN u.role r WHERE u.username = :username AND u.password = :password")
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Optional<User> findByEmail(String email);

}