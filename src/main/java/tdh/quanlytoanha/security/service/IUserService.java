package tdh.quanlytoanha.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tdh.quanlytoanha.dtos.UserDTO;
import tdh.quanlytoanha.security.entities.Role;
import tdh.quanlytoanha.security.entities.User;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User save(User user);
    void remove(Integer id);
    User registerNewUser(UserDTO userDTO) throws Exception;
    Optional<User> findByUsername(String username);
    List<User> findAllUsers(); // Phương thức để lấy tất cả người dùng

    // Thêm phương thức lưu vai trò

    void saveRole(Role role);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByEmail(String email);
    
}
