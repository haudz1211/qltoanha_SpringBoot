package tdh.quanlytoanha.dtos;

import lombok.Data;

@Data
public class MonthlyFeeOfCompanyDTO{
    private MonthDTO month;
    private double totalAmount;
    private CompanyDTO company;
}
