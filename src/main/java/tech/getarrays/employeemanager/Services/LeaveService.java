package tech.getarrays.employeemanager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.Entities.Employee;
import tech.getarrays.employeemanager.Entities.Leave;
import tech.getarrays.employeemanager.Repositories.EmployeeRepo;
import tech.getarrays.employeemanager.Repositories.LeaveRepo;
import tech.getarrays.employeemanager.exception.UserNotFoundException;

import javax.transaction.Transactional;

import java.time.Period;
import java.util.List;


@Service
@Transactional
public class LeaveService  {
   private final LeaveRepo leaveRepo;
   private final EmployeeRepo employeeRepo ;

    @Autowired
    public LeaveService(LeaveRepo leaveRepo, EmployeeRepo employeeRepo) {
        this.leaveRepo = leaveRepo;
        this.employeeRepo = employeeRepo;
    }



    public Leave addLeave(Leave leave) {
        return leaveRepo.save(leave);
    }

    public List<Leave> findAllLeaves() {
        return leaveRepo.findAll();
    }

    public Leave updateLeave(Leave leave) {
        return leaveRepo.save(leave);
    }

    public Leave findLeaveById(long id) {
        return leaveRepo.findById((int)id)
                .orElseThrow(() -> new UserNotFoundException("Leave by id " + id + " was not found"));
    }

    public void deleteLeave(Integer id){
        leaveRepo.deleteById(id);
    }

//    public void add_Leave_To_employee(long id_employee, Integer id_leave){
//        Leave leave = leaveRepo.findById(id_leave).orElse(null);
//        Employee employee = employeeRepo.findById(id_employee).orElse(null);
//
//     employee.getLeaves().add(leave);
//    }
    public Integer nbrs_leave(Leave leave){
         Period diff = Period.between(leave.getStart_date(), leave.getEnd_date()) ;
         return  Cast(diff)  ;
    }
    public Integer Cast(Period diff){
         return  diff.getDays() ;
    }

//    public void accept_leave(Integer id_leave){
//        Leave leave = leaveRepo.findById(id_leave).orElse(null);
//        Employee employee = employeeRepo.findById(leave.getEmployee().getId()).orElse(null);
//        leave.setStatus(Leave.leaves.Accepted);
//        employee.setLeave_balance(employee.getLeave_balance()-nbrs_leave(leave));
//    }
    public void refuse_leave(Integer id_leave){
        Leave leave = leaveRepo.findById(id_leave).orElse(null);
        leave.setStatus(Leave.leaves.Refused);
    }



}
    

