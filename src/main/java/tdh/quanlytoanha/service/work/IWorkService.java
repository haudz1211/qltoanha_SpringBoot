package tdh.quanlytoanha.service.work;

import tdh.quanlytoanha.dtos.WorkDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface IWorkService extends IGeneralService<WorkDTO> {
    void createNewWork(Integer empId, WorkDTO workDTO);
    void updateWork(Integer workId, Integer empId, WorkDTO workDTO);
    List<WorkDTO> findWorkByTitle(String title);
    List<WorkDTO> findWorkByBuildingEmployeeId(Integer empId);
    List<WorkDTO> findWorkByTitleContainingAndBuildingEmployeeId(String title, Integer id);
}
