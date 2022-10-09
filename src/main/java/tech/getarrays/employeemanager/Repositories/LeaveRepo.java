package tech.getarrays.employeemanager.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.getarrays.employeemanager.Entities.Leave;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Integer> {
}
