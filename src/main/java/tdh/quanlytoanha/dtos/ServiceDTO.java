package tdh.quanlytoanha.dtos;

import lombok.Data;

@Data
public class ServiceDTO {
    private int id;
    private String name;
    private String type;
    private double price;
    private int required;
}
