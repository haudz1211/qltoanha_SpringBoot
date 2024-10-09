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
import tdh.quanlytoanha.security.entities.Role;
import tdh.quanlytoanha.security.entities.User;
import tdh.quanlytoanha.security.repo.IRoleRepository;
import tdh.quanlytoanha.security.repo.IUserRepository;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository; // Tiêm IRoleRepository
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
        // Mã hóa mật khẩu trước khi lưu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
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
    // Triển khai phương thức lưu vai trò
    @Override
    public void saveRole(Role role) {
        roleRepository.save(role); // Sử dụng roleRepository để lưu vai trò
    }


    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }



    public String generateOtp() {
        int otpLength = 6; // Độ dài mã OTP
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(otpLength);
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10)); // Tạo số ngẫu nhiên từ 0 đến 9
        }
        return otp.toString();
    }

    // Phương thức lưu OTP nếu cần
    public void storeOtp(String email, String otp) {
        // Lưu OTP vào bộ nhớ tạm thời (có thể dùng Map hoặc một giải pháp khác)

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null); // Sử dụng Optional để xử lý giá trị không có
    }


//    @Override
//    public boolean existsByEmail(String email) {
//        return userRepository.existsByEmail(email); // Nếu bạn đã thêm phương thức này
//    }
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent(); // Xác minh rằng userRepository đang hoạt động chính xác
    }

}