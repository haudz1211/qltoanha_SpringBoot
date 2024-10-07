package tdh.quanlytoanha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdh.quanlytoanha.entities.MonthEntity;

public interface IMonthRepository extends JpaRepository<MonthEntity, Integer> {
}