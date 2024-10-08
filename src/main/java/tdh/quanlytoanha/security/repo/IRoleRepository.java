package tdh.quanlytoanha.security.repo;


import tdh.quanlytoanha.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
//    Role findByName(String name);
    Role findByName(String name);

}
