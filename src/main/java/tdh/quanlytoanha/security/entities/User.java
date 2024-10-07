    package tdh.quanlytoanha.security.entities;

    import lombok.Data;

    import javax.persistence.*;

    @Entity
    @Data
    @Table(name = "users") // Tên bảng là 'user'
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(unique = true, nullable = false) // Username phải duy nhất và không thể null
        private String username;

        @Column(nullable = false) // Password không thể null
        private String password;

        @Column(nullable = false) // FullName không thể null
        private String fullName;

        @ManyToOne // Quan hệ ManyToOne với Role
        @JoinColumn(name = "role_id", nullable = false) // Cột role_id sẽ được thêm vào bảng user
        private Role role; // Mỗi user chỉ có một role
    }
