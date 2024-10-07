package tdh.quanlytoanha.service.monthly_bill;

import tdh.quanlytoanha.dtos.MonthlyBillDTO;


import java.util.List;

public interface IMonthlyBillService {
    List<MonthlyBillDTO> findMonthlyBillsOfCompanyInAMonth(Integer companyId, Integer monthId);
}
