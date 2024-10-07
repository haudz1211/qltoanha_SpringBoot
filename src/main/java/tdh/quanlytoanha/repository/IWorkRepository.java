package tdh.quanlytoanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdh.quanlytoanha.entities.WorkEntity;

import java.util.List;

public interface IWorkRepository extends JpaRepository<WorkEntity, Integer> {
    List<WorkEntity> findWorkEntitiesByTitleContaining(String title);
    List<WorkEntity> findWorkEntitiesByBuildingEmployee_Id(Integer id);
    List <WorkEntity> findWorkEntitiesByTitleContainingAndBuildingEmployee_Id(String title, Integer id);
}
