package tdh.quanlytoanha.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@Table(name = "company_employee")
@Entity
@NoArgsConstructor
public class CompanyEmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String socialId;
    private String name;
    private Date dateOfBirth;
    private String phoneNo;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;
}
