package tdh.quanlytoanha.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "company")
@NoArgsConstructor
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String taxCode;
    private double authorizedCapital;
    private String phoneNo;
    private String activeField;
    private String email;  // New email field added

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CompanyEmployeeEntity> employees;
}
