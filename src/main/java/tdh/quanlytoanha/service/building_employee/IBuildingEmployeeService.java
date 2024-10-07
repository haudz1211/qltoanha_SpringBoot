package tdh.quanlytoanha.service.building_employee;

import tdh.quanlytoanha.dtos.BuildingEmployeeDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface IBuildingEmployeeService extends IGeneralService<BuildingEmployeeDTO> {
    void createNewBuildingEmployeeBySalaryId(Integer salaryId, BuildingEmployeeDTO buildingEmployeeDTO);
    void updateBuildingEmployee(Integer empId, Integer salaryId, BuildingEmployeeDTO buildingEmployeeDTO);
    List<BuildingEmployeeDTO> findBuildingEmployeeByName(String name);
}
