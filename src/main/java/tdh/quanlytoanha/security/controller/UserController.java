package tdh.quanlytoanha.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tdh.quanlytoanha.Email.EmailService;
import tdh.quanlytoanha.dtos.OtpDTO; // Import DTO cho OTP
import tdh.quanlytoanha.dtos.UserDTO;
import tdh.quanlytoanha.security.entities.Role;
import tdh.quanlytoanha.security.entities.User;
import tdh.quanlytoanha.security.service.IRoleService;
import tdh.quanlytoanha.security.service.IUserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    // Lưu trữ mã OTP tạm thời (có thể dùng cache hoặc DB tạm thời)
    private Map<String, String> otpStorage = new HashMap<>();

    // Tạo mã OTP ngẫu nhiên
    private String generateOtp() {
        int otp = (int)(Math.random() * 9000) + 1000; // Tạo OTP 4 chữ số
        return String.valueOf(otp);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userService.existsByUsername(userDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Tên người dùng đã tồn tại!");
        }
        // Kiểm tra xem email đã tồn tại chưa
        if (userService.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email đã tồn tại!");
        }

        // Tạo mã OTP
        String otp = generateOtp();
        otpStorage.put(userDTO.getEmail(), otp); // Lưu mã OTP vào tạm thời
        // Gửi OTP đến email
        emailService.sendOtpEmail(userDTO.getEmail(), otp);

        return ResponseEntity.ok("Mã OTP đã được gửi đến email của bạn.");
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpDTO otpDTO) {
        // Lấy mã OTP đã lưu từ bộ nhớ
        String storedOtp = otpStorage.get(otpDTO.getEmail());
        System.out.println("Mã OTP đã lưu: " + storedOtp); // Log mã OTP đã lưu
        System.out.println("Mã OTP được gửi: " + otpDTO.getOtp()); // Log mã OTP gửi lên

        // Kiểm tra mã OTP
        if (storedOtp == null || !storedOtp.trim().equals(otpDTO.getOtp().trim())) {
            return ResponseEntity.badRequest().body("Mã OTP không hợp lệ.");
        }

        // Nếu mã OTP hợp lệ, tiến hành lưu người dùng vào cơ sở dữ liệu
        User user = new User();
        user.setUsername(otpDTO.getUsername());

        // Kiểm tra xem mật khẩu có hợp lệ không
        if (otpDTO.getPassword() == null || otpDTO.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Mật khẩu không thể là null hoặc trống.");
        }
        user.setPassword(passwordEncoder.encode(otpDTO.getPassword()));
        user.setFullName(otpDTO.getFullName());
        user.setEmail(otpDTO.getEmail());

        // Lấy Role mặc định (ví dụ: ROLE_USER) từ cơ sở dữ liệu
        Role role = roleService.findByName("ROLE_USER"); // Thay đổi tên role nếu cần
        if (role == null) {
            return ResponseEntity.badRequest().body("Role không hợp lệ.");
        }
        user.setRole(role); // Gán role cho user

        // Lưu người dùng vào cơ sở dữ liệu
        try {
            userService.save(user);
            // Xóa mã OTP đã lưu sau khi xác minh thành công
            otpStorage.remove(otpDTO.getEmail());
            return ResponseEntity.ok("Xác nhận thành công! Bạn có thể đăng nhập.");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi chi tiết
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }



    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());

        try {
            userService.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Tạo người dùng không thành công. Vui lòng thử lại.");
        }
    }



    //quên mk
    @PostMapping("/sendOtp")
    public ResponseEntity<?> sendOtpForPasswordReset(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail(); // Lấy email từ đối tượng UserDTO

        // Kiểm tra xem email có tồn tại trong hệ thống không
        if (!userService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email không tồn tại trong hệ thống.");
        }

        // Tạo mã OTP
        String otp = generateOtp();
        System.out.println("Mã OTP được tạo: " + otp); // In ra mã OTP được tạo

        // Lưu mã OTP vào tạm thời với thời gian hết hạn
        otpStorage.put(email, otp);
        System.out.println("Mã OTP đã lưu: " + otpStorage.get(email)); // Kiểm tra giá trị OTP đã lưu

        // Gửi OTP đến email
        emailService.sendOtpEmail(email, otp);

        return ResponseEntity.ok("Mã OTP đã được gửi đến email của bạn.");
    }


    @PostMapping("/verifyOtpForPasswordReset")
    public ResponseEntity<?> verifyOtpForPasswordReset(@RequestBody OtpDTO otpDTO) {
        String email = otpDTO.getEmail();
        String storedOtp = otpStorage.get(email); // Lấy mã OTP đã lưu

        // Kiểm tra mã OTP
        if (storedOtp == null || !storedOtp.trim().equals(otpDTO.getOtp().trim())) {
            return ResponseEntity.badRequest().body("Mã OTP không hợp lệ.");
        }

        // Nếu mã OTP hợp lệ, trả về thông báo cho phép người dùng đặt lại mật khẩu
        return ResponseEntity.ok("Mã OTP xác thực thành công. Bạn có thể đặt lại mật khẩu.");
    }


    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail(); // Lấy email

        // Kiểm tra xem email có tồn tại trong hệ thống không
        if (!userService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email không tồn tại trong hệ thống.");
        }

        // Cập nhật mật khẩu cho người dùng
        try {
            User user = userService.findByEmail(email);
            if (user == null) {
                return ResponseEntity.badRequest().body("Người dùng không tồn tại.");
            }

            String newPassword = userDTO.getPassword();

            // In ra mật khẩu mới nhận được để kiểm tra
            System.out.println("Mật khẩu mới nhận được: " + newPassword);

            if (newPassword == null || newPassword.isEmpty()) {
                return ResponseEntity.badRequest().body("Mật khẩu mới không hợp lệ.");
            }

            user.setPassword(passwordEncoder.encode(newPassword)); // Mã hóa mật khẩu mới
            userService.save(user);
            otpStorage.remove(email); // Xóa OTP đã sử dụng
            return ResponseEntity.ok("Mật khẩu đã được đặt lại thành công.");
        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi chi tiết
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }






}
