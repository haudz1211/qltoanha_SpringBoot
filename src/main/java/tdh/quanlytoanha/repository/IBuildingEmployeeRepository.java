package tdh.quanlytoanha.repository;

import tdh.quanlytoanha.entities.BuildingEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBuildingEmployeeRepository extends JpaRepository<BuildingEmployeeEntity, Integer> {
    List<BuildingEmployeeEntity> findBuildingEmployeeEntitiesByNameContaining(String name);
}