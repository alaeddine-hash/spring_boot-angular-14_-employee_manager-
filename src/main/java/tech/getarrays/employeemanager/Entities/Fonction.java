package tech.getarrays.employeemanager.Entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "fonctions")
@Data
public class Fonction implements Serializable {
    public enum Fonctions {
      Simple,
        Team_Leader,
        Manager,
        Top_Manager,
        responsible_RH,
        User,
        Admin
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_fonction ;
    @Column(nullable = false)
    private LocalDate creation_date ;
    @Column(nullable = false)
    private LocalDate update_date ;
    @Column(nullable = false)
    private Fonctions name;


//    @ManyToMany
//    @JoinTable(name = "employee_fonctions", joinColumns = @JoinColumn(name = "id_fonction"),inverseJoinColumns = @JoinColumn(name = "id"))
//
//    private List<Employee> employees ;
@ManyToMany(mappedBy = "fonctions")
private List<Employee> employees;


}
