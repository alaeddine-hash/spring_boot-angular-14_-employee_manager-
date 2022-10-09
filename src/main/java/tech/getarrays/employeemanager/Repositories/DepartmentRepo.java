package tech.getarrays.employeemanager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.getarrays.employeemanager.Entities.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {
}
