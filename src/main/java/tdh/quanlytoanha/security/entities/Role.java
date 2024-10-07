package tdh.quanlytoanha.security.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "roles") // Tên bảng là 'roles'
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true) // Tên vai trò phải duy nhất và không thể null
    private String name;

    @OneToMany(mappedBy = "role") // Thiết lập quan hệ ngược lại
    private Set<User> users = new HashSet<>(); // Một role có nhiều user
}
