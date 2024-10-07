package tdh.quanlytoanha.service.service_contract;

import tdh.quanlytoanha.dtos.ServiceContractDTO;
import tdh.quanlytoanha.service.IGeneralService;
import java.util.List;
import java.util.Optional;

public interface IServiceContractService extends IGeneralService<ServiceContractDTO> {
    List<ServiceContractDTO> findAllServiceContractOfCompany(Integer companyId);
    Optional<ServiceContractDTO> createServiceContract(Integer companyId, Integer serviceId, ServiceContractDTO serviceContractDTO);
    List<ServiceContractDTO> findServiceContractByServiceName(String serviceName);
}
