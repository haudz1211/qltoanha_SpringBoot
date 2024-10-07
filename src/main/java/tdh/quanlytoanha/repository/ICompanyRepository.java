package tdh.quanlytoanha.repository;

import tdh.quanlytoanha.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICompanyRepository extends JpaRepository<CompanyEntity, Integer> {
    List<CompanyEntity> findCompanyEntitiesByNameContaining(String name);
}