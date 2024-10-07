package tdh.quanlytoanha.service.company_employee;

import tdh.quanlytoanha.dtos.CompanyEmployeeDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface ICompanyEmployeeService extends IGeneralService<CompanyEmployeeDTO> {
    public List<CompanyEmployeeDTO> findAllEmployeeOfCompany(Integer id);
    public int countCompanyEmployeesByCompanyID(Integer companyId);
    List<CompanyEmployeeDTO> findEmployeesByNameAndCompanyId(String empName,Integer companyId);

}
