package tech.getarrays.employeemanager.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Table(name = "Leaves")
public class Leave implements Serializable {
    public enum leaves {
        Pending,
        Accepted,
        Refused
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_leave ;
    @Column(nullable = false)
    private LocalDate start_date;
    @Column(nullable = false)
    private LocalDate end_date ;
    @Column(nullable = false)
    private leaves status ;


//@ManyToOne
//@JsonIgnore
 //@JoinColumn(name = "employee_id")
//private Employee employee ;
@ManyToMany(mappedBy = "fonctions")
private List<Employee> employees ;
}
