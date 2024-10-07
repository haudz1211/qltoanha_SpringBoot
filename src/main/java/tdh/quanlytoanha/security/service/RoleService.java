package tdh.quanlytoanha.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdh.quanlytoanha.security.entities.Role;
import tdh.quanlytoanha.security.entities.User;
import tdh.quanlytoanha.security.repo.IRoleRepository;
import tdh.quanlytoanha.security.repo.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Role findByName(String roleName) {
        Optional<Role> role = Optional.ofNullable(roleRepository.findByName(roleName));
        if (role.isPresent()) {
            return role.get();
        } else {
            throw new RuntimeException("Vai trò không tồn tại");
        }
    }

    @Override
    public List<User> findAllUsers() {
        try {
            return iUserRepository.findAll(); // Gọi phương thức từ repository
        } catch (Exception e) {
            e.printStackTrace(); // In ra thông báo lỗi
            throw e; // Ném lại lỗi để có thể xử lý ở controller
        }
    }
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void remove(Integer id) {
        roleRepository.deleteById(id);
    }
}


//@Service
//public class RoleService implements IRoleService {
//    @Autowired
//    private IRoleRepository roleRepository;
//
//    @Override
//    public List<Role> findAll() {
//        return roleRepository.findAll();
//    }
//
//    @Override
//    public Optional<Role> findById(Integer id) {
//        return roleRepository.findById(id);
//    }
//
//    @Override
//    public Role save(Role role) {
//        return roleRepository.save(role);
//    }
//
//    @Override
//    public void remove(Integer id) {
//        roleRepository.deleteById(id);
//    }
//
//    @Override
//    public Role findByName(String name) {
//        return roleRepository.findByName(name);
//    }
//
//
//}