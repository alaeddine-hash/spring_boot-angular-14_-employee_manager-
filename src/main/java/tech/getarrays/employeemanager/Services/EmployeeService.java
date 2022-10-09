package tech.getarrays.employeemanager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.Entities.Department;
import tech.getarrays.employeemanager.Entities.Fonction;
import tech.getarrays.employeemanager.Entities.Leave;
import tech.getarrays.employeemanager.Repositories.DepartmentRepo;
import tech.getarrays.employeemanager.Repositories.FonctionRepo;
import tech.getarrays.employeemanager.Repositories.LeaveRepo;
import tech.getarrays.employeemanager.exception.UserNotFoundException;
import tech.getarrays.employeemanager.Entities.Employee;
import tech.getarrays.employeemanager.Repositories.EmployeeRepo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static tech.getarrays.employeemanager.Entities.Leave.leaves.Accepted;
import static tech.getarrays.employeemanager.Entities.Leave.leaves.Refused;

@Service
@Transactional
public class EmployeeService implements UserDetailsService {
    private static EmployeeRepo employeeRepo;
    private static LeaveService leaveService;
    private final LeaveRepo leaveRepo ;
    private final FonctionRepo fonctionRepo;

    private final DepartmentRepo departmentRepo ;
    private final PasswordEncoder passwordEncoder;
    private final FonctionService fonctionService ;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, LeaveRepo leaveRepo, FonctionRepo fonctionRepo, DepartmentRepo departmentRepo, PasswordEncoder passwordEncoder, FonctionService fonctionService) {
        this.employeeRepo = employeeRepo;
        this.leaveRepo = leaveRepo;
        this.fonctionRepo = fonctionRepo;
        this.departmentRepo = departmentRepo;
        this.passwordEncoder = passwordEncoder;
        this.fonctionService = fonctionService;
    }

    public Employee addEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

    public List<Leave> Get_leaves_by_id_Employee(long id){
        Employee employee = findEmployeeById(id);
        return  employee.getLeaves();
    }

    public List<Fonction> Get_fonctions_by_id_Employee(long id) {
        Employee employee = findEmployeeById(id);
        return employee.getFonctions();

    }

    public List<Leave> Get_historical_leaves(long id){
        List<Leave> leaves = new ArrayList<>() ;
              leaves  = Get_leaves_by_id_Employee(id) ;
        List<Leave> Res = new ArrayList<>() ;
        for (Leave leave: leaves){
            if (leave.getStatus()!= Leave.leaves.Pending){
                Res.add(leave);
            }
        }
        return Res ;
    }
    public List<Leave> Get_In_progress_leaves(long id){
        List<Leave> leaves = new ArrayList<>() ;
        leaves  = Get_leaves_by_id_Employee(id) ;
        List<Leave> Res = new ArrayList<>() ;
        for (Leave leave: leaves){
            if (leave.getStatus() == Leave.leaves.Pending){
                Res.add(leave);
            }
        }
        return Res ;
    }
   public void add_Leave_To_employee(long id_employee, Leave s ){

     Employee employee = employeeRepo.findById(id_employee).orElse(null);
       Leave leave = leaveRepo.save(s);
       List<Leave> L = new ArrayList<>() ;
       L = employee.getLeaves() ;
       L.add(leave);
       employee.setLeaves(L);
  }


    public void add_Fonction_To_employee(long id_employee, Fonction s){

        Employee employee = employeeRepo.findById(id_employee).orElse(null);
        Fonction fonction = fonctionRepo.save(s);
        List<Fonction> L = new ArrayList<>() ;
        L = employee.getFonctions() ;
        L.add(fonction);
        employee.setFonctions(L);
    }




    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    private String toString(Fonction.Fonctions name) {
        return String.valueOf(name);
    }

    public List<Employee> find_Employee_By_Fonction(String nomFunction){
        List<Fonction> fonctions = new ArrayList<>();
        List<Employee> Res = new ArrayList<>() ;
        fonctions =  fonctionRepo.findAllByName(nomFunction);

        for (Fonction fonction : fonctions){
            Employee employee = employeeRepo.findEmployeesByFonctions(fonction);
            Res.add(employee);
        }
         return Res ;
   }

    public List<Department> Get_departments_by_id_Employee(long id) {
        Employee employee = findEmployeeById(id);
        return employee.getDepartments();
    }

    public void add_Department_To_employee(long id, Department s) {

        Employee employee = employeeRepo.findById(id).orElse(null);
        Department department = departmentRepo.save(s);
        List<Department> L = new ArrayList<>() ;
        L = employee.getDepartments() ;
        L.add(department);
        employee.setDepartments(L);

    }

    public List<String> Get_fonctions_names_list_by_id_Employee(long id) {
        Employee employee = findEmployeeById(id);
        List<String> list = new ArrayList<>() ;
        for (Fonction fonction : employee.getFonctions()){
            list.add(String.valueOf(fonction.getName()));
        }
        return list;
    }

    public List<String> Get_departments_names_list_by_id_Employee(long id) {
        Employee employee = findEmployeeById(id);
        List<String> list = new ArrayList<>() ;
        for (Department department : employee.getDepartments()){
            list.add(String.valueOf(department.getNom()));
        }
        return list;
    }
    public void accept_Leave_To_employee(long id_employee, Leave s ){

        Employee employee = employeeRepo.findById(id_employee).orElse(null);
        Leave leave0 = s ;
//        employee.setLeave_balance(employee.getLeave_balance()- leaveService.nbrs_leave(leave0));
        s.setStatus(Accepted);
        Leave leave = leaveRepo.save(s);
        List<Leave> L = new ArrayList<>() ;
        L = employee.getLeaves() ;
        L.remove(leave0);
        L.add(leave);
        employee.setLeaves(L);
    }

    public void refuse_Leave_To_employee(long id_employee, Leave s ) {

        Employee employee = employeeRepo.findById(id_employee).orElse(null);
        Leave leave0 = s;
        s.setStatus(Refused);
        Leave leave = leaveRepo.save(s);
        List<Leave> L = new ArrayList<>();
        L = employee.getLeaves();
        L.remove(leave0);
        L.add(leave);
        employee.setLeaves(L);
    }}

