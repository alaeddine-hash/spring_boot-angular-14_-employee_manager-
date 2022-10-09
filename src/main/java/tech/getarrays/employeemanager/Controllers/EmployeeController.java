package tech.getarrays.employeemanager.Controllers;

import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.Entities.Department;
import tech.getarrays.employeemanager.Entities.Employee;
import tech.getarrays.employeemanager.Entities.Fonction;
import tech.getarrays.employeemanager.Entities.Leave;
import tech.getarrays.employeemanager.Services.EmployeeService;
import java.util.List;



@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    final
    EmployeeService employeeService ;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    @ResponseBody
    public Employee add_New_Employee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Employee> all_employees(){
        return employeeService.findAllEmployees();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public Employee employee_id(@PathVariable("id") long id){
        return employeeService.findEmployeeById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public void Mettre_A_jours_employee(@RequestBody Employee employee){employeeService.updateEmployee(employee); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void supp_employe(@PathVariable("id") long id){employeeService.deleteEmployee(id); }



    @PutMapping("/add_Leave/{id}")
    @ResponseBody
    public void add_Leave_To_employee(@RequestBody @PathVariable("id") long id,@RequestBody Leave leave){
       employeeService.add_Leave_To_employee(id, leave);
    }

    @PutMapping("/add_Fonction/{id}")
    @ResponseBody
    public void add_Fonction_To_employee(@RequestBody @PathVariable("id") long id,@RequestBody Fonction fonction){
        employeeService.add_Fonction_To_employee(id, fonction);
    }
    @PutMapping("/add_department/{id}")
    @ResponseBody
    public void add_Department_To_employee(@RequestBody @PathVariable("id") long id,@RequestBody Department department){
        employeeService.add_Department_To_employee(id, department);
    }


    @GetMapping("/all/{fonction_nom}")
    @ResponseBody
    public List<Employee> find_Employee_By_Fonction(@PathVariable("fonction_nom") String fonction_nom){

        return employeeService.find_Employee_By_Fonction(fonction_nom);
  }

    @GetMapping("/all/leaves/{id}")
    @ResponseBody
    public List<Leave> Get_leaves_by_id_Employee(@PathVariable("id") long id){
        return employeeService.Get_leaves_by_id_Employee(id);
    }

    @GetMapping("/all/fonctions/{id}")
    @ResponseBody
    public List<Fonction> Get_fonctions_by_id_Employee(@PathVariable("id") long id){
        return employeeService.Get_fonctions_by_id_Employee(id);
    }


    @GetMapping("/all/departments/{id}")
    @ResponseBody
    public List<Department> Get_deaprtments_by_id_Employee(@PathVariable("id") long id){
        return employeeService.Get_departments_by_id_Employee(id);}
    @GetMapping("/fonctionsNamesList/{id}")
    @ResponseBody
    public List<String> Get_fonctions_names_list_by_id_Employee(@PathVariable("id") long id){
        return employeeService.Get_fonctions_names_list_by_id_Employee(id);
    }
    @GetMapping("/departmentsNamesList/{id}")
    @ResponseBody
    public List<String> Get_departments_names_list_by_id_Employee(@PathVariable("id") long id){
        return employeeService.Get_departments_names_list_by_id_Employee(id);
    }

    @PutMapping("/accept_Leave/{id}")
    @ResponseBody
    public void accept_leave_for_employee(@RequestBody @PathVariable("id") long id,@RequestBody Leave leave){
        employeeService.accept_Leave_To_employee(id, leave);


    }

    @PutMapping("/refuse_Leave/{id}")
    @ResponseBody
    public void refuse_leave_for_employee(@RequestBody @PathVariable("id") long id,@RequestBody Leave leave){
        employeeService.refuse_Leave_To_employee(id, leave);
    }


}
