package tdh.quanlytoanha.service.monthly_service_bill;

import tdh.quanlytoanha.dtos.MonthlyServiceBillDTO;
import tdh.quanlytoanha.dtos.ServiceContractDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.Date;
import java.util.List;


public interface IMonthlyServiceBillService extends IGeneralService<MonthlyServiceBillDTO> {
    double calculateMoney(Date startDate, Date endDate, ServiceContractDTO serviceContractDTO);
    List<MonthlyServiceBillDTO> findMonthlyServiceBillsOfCompanyInAMonth(Integer companyId,Integer monthId);
}
