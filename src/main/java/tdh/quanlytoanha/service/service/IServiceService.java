package tdh.quanlytoanha.service.service;


import tdh.quanlytoanha.dtos.ServiceDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface IServiceService extends IGeneralService<ServiceDTO> {
     List<ServiceDTO> findAllUnregisterdServices(Integer companyId);
     List<ServiceDTO> findAllUnregisterdServicesByServiceName(Integer companyId, String serviceName);
     List<ServiceDTO> findServicesByName(String name);
}
