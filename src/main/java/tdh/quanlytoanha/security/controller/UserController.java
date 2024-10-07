package tdh.quanlytoanha.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tdh.quanlytoanha.security.entities.User;
import tdh.quanlytoanha.security.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers(); // Gọi phương thức findAllUsers()
        return ResponseEntity.ok(users); // Trả về danh sách người dùng
    }
}
