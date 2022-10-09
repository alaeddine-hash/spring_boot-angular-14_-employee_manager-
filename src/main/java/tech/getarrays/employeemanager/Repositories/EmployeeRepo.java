package tech.getarrays.employeemanager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.getarrays.employeemanager.Entities.Employee;
import tech.getarrays.employeemanager.Entities.Fonction;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {


 Optional<Employee> findByUsername(String username);

  @Query(value = " SELECT e from Employee e where e.fonctions = ?1 ")
  Employee findEmployeesByFonctions(Fonction fonction);
////   Optional<Employee>  findByIdFonction(Integer id_fonction);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);


}
