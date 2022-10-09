package tech.getarrays.employeemanager.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Entity
@Data
@Table(name = "departments")
public class Department implements Serializable {
    public enum Departments {
        Global,
        constance,
        neuchatel
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_dep ;
    @Column(nullable = false)
    private LocalDate creation_date ;
    @Column(nullable = false)
    private LocalDate update_date ;
    @Column(nullable = false)
    private Departments nom ;

    //   @ManyToMany
    // @JoinTable(name = "employee_departments", joinColumns = @JoinColumn(name = "id_dep"),inverseJoinColumns = @JoinColumn(name = "id"))

    //   private List<Employee> employees ;
    @ManyToMany(mappedBy = "departments")
    private List<Employee> employees;


}
