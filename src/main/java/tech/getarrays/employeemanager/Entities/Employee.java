package tech.getarrays.employeemanager.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employees")
@Data
//@AllArgsConstructor
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
  // @Column(nullable = false)
//    updatable = false
    private Long id;
   // @Column(nullable = false)
    private String name ;
   // @Column(nullable = false)
    private String lastname ;
 //   @Column(nullable = false)
    private LocalDate birthday ;
  //  @Column(nullable = false)
    private String phone ;
   // @Column(nullable = false)
    private LocalDate hiring_date ;
  //  @Column(nullable = false)
    private LocalDate contract_end_date ;
  //  @Column(nullable = false)
    private String email ;
  //  @Column(nullable = false)
    private String username ;
  //  @Column(nullable = false)
    private String password ;

  //  @Column(nullable = false)
    private Integer leave_balance ;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_fonctions", joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "id_fonction"))
    private List<Fonction> fonctions ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_departments", joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "id_dep"))
    private List<Department> departments ;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_Leaves", joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "id_leave"))
    private List<Leave> leaves;

    public Employee(String username,String name, String lastname,LocalDate birthday,String phone,LocalDate hiring_date,LocalDate
            contract_end_date,Integer leave_balance, String email, String password ) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.phone = phone;
        this.hiring_date = hiring_date;
        this.contract_end_date = contract_end_date;
        this.leave_balance = leave_balance ;
        this.email = email;
        this.password = password;

    }

    public Employee() {

    }




}


