package tdh.quanlytoanha.service.salary;

import tdh.quanlytoanha.dtos.SalaryDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface ISalaryService extends IGeneralService<SalaryDTO> {
    List<SalaryDTO> findSalariesByServiceId(Integer serviceId);

    void createNewSalaryByServiceId(Integer serviceId,SalaryDTO salaryDTO);
}
