package tdh.quanlytoanha.service.contract;

import tdh.quanlytoanha.dtos.ContractDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface IContractService extends IGeneralService<ContractDTO> {
     double getSumOfRentedArea(Integer companyId);
     double getSumOfRentedAreaFloor(Integer floorId);
     void createContract(Integer companyId, Integer floorId, ContractDTO contractDTO );
     List<ContractDTO> getContractsByFloorId(Integer floorId);
     List<ContractDTO> getContractsByCompanyId(Integer companyId);
}
