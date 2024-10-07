package tdh.quanlytoanha.dtos;

import lombok.Data;

@Data
public class MonthlyServiceBillDTO {
    private int id;
    private double totalAmount;
    private ServiceContractDTO serviceContract;
    private MonthDTO month;
}
