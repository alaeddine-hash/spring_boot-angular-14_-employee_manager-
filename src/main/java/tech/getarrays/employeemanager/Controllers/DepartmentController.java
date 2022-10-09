package tech.getarrays.employeemanager.Controllers;

import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.Entities.Department;
import tech.getarrays.employeemanager.Services.DepartmentService;


import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    final
        DepartmentService departmentService ;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }



    @PostMapping("/add")
    @ResponseBody
    public Department add_New_Department(@RequestBody Department department) {
        departmentService.addDepartment(department);
        return department;
    }


    @GetMapping("/all")
    @ResponseBody
    public List<Department> all_Departments(){
        return departmentService.findAllDepartment();
    }


    @GetMapping("/id/{id}")
    @ResponseBody
    public Department department_id(@PathVariable("id") Integer id){
        return departmentService.findDepartmentById(id);
    }


    @PutMapping("/update")
    @ResponseBody
    public void Mise_A_jours_Department(@RequestBody Department department){departmentService.updateDepartment(department); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void supp_Department(@PathVariable("id") Integer id){departmentService.deleteDepartment(id); }


}
