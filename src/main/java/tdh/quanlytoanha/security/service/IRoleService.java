package tdh.quanlytoanha.security.service;

import tdh.quanlytoanha.security.entities.Role;
import tdh.quanlytoanha.security.entities.User;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);

    List<User> findAllUsers();
}