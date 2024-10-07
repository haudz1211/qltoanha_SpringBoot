package tdh.quanlytoanha.service.company;

import tdh.quanlytoanha.dtos.CompanyDTO;
import tdh.quanlytoanha.dtos.MonthlyFeeOfCompanyDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface ICompanyService extends IGeneralService<CompanyDTO> {
    List<MonthlyFeeOfCompanyDTO> getMonthlyFeeOfCompany(Integer monthId);
    List<MonthlyFeeOfCompanyDTO> getFeeOfCompanies();
    CompanyDTO update(CompanyDTO companyDTO);
    List<CompanyDTO> findCompaniesByName(String name);
}
