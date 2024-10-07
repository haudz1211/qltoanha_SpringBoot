package tdh.quanlytoanha.service.monthly_salary;

import tdh.quanlytoanha.dtos.MonthlySalaryDTO;
import tdh.quanlytoanha.service.IGeneralService;

import java.util.List;

public interface IMonthlySalaryService extends IGeneralService<MonthlySalaryDTO> {
    public List<MonthlySalaryDTO> getMonthlySalariesByMonthId(Integer monthId);
}
