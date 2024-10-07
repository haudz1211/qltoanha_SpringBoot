package tdh.quanlytoanha.security.service;

//@Service
//public class UserService implements IUserService {
//    @Autowired
//    private IUserRepository userRepository;
//
//
//
//    @Autowired
//    private RoleService roleService; // Đảm bảo bạn đã inject RoleService
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public Optional<User> findById(Integer id) {
//        return userRepository.findById(id);
//    }
//
//    @Override
//    public User save(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepository.save(user);
//    }
//
//    @Override
//    public void remove(Integer id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        if (!userOptional.isPresent()) {
//            throw new UsernameNotFoundException(username);
//        }
//        return UserPrinciple.build(userOptional.get());
//    }
//
//    @Override
//    public User registerNewUser(UserDTO userDTO) throws Exception {
//        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
//            throw new Exception("User already exists");
//        }
//
//        // Tạo đối tượng User
//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//        user.setFullName(userDTO.getFullName());
//
//        // Thiết lập vai trò cho người dùng
//        Role userRole = roleService.findByName("ROLE_USER"); // Gọi phương thức không tĩnh
//        if (userRole != null) {
//            user.setRole(userRole);
//        } else {
//            throw new Exception("Vai trò không tồn tại.");
//        }
//
//        return userRepository.save(user); // Lưu người dùng vào DB
//    }
//
//    @Override
//    public Optional<User> findByUsername(String username) {
//        return Optional.empty();
//    }
//
//
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tdh.quanlytoanha.dtos.UserDTO;
import tdh.quanlytoanha.security.UserPrinciple;
import tdh.quanlytoanha.security.entities.User;
import tdh.quanlytoanha.security.repo.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public User registerNewUser(UserDTO userDTO) throws Exception {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return UserPrinciple.build(userOptional.get());
    }



    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll(); // Gọi phương thức findAll() từ repository
    }
}