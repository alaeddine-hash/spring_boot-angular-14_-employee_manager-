package tech.getarrays.employeemanager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.Entities.Department;
import tech.getarrays.employeemanager.Repositories.DepartmentRepo;
import tech.getarrays.employeemanager.exception.UserNotFoundException;

import java.util.List;
@Service

public class DepartmentService {
    private final DepartmentRepo departmentRepo;
    @Autowired
    public DepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }
    public Department addDepartment(Department department) {
        return departmentRepo.save(department);
    }

    public List<Department> findAllDepartment() {
        return departmentRepo.findAll();
    }

    public Department updateDepartment(Department department) {
        return departmentRepo.save(department);
    }

    public Department findDepartmentById(int id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Leave by id " + id + " was not found"));
    }

    public void deleteDepartment(Integer id){
        departmentRepo.deleteById(id);
    }
}
