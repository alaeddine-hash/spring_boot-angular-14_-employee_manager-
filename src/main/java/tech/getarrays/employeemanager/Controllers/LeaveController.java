package tech.getarrays.employeemanager.Controllers;

import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.Entities.Leave;
import tech.getarrays.employeemanager.Services.LeaveService;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {
    final LeaveService leaveService ;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    @PostMapping("/add")
    @ResponseBody
    public Leave add_New_Leave(@RequestBody Leave leave) {
        leaveService.addLeave(leave);
        return leave; }

    @GetMapping("/all")
    @ResponseBody
    public List<Leave> all_leaves(){
        return leaveService.findAllLeaves();
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public Leave leave_id(@PathVariable("id") long id){
        return leaveService.findLeaveById(id);
    }
    @PutMapping("/update")
    @ResponseBody
    public void Mettre_A_jours_leave(@RequestBody Leave leave){leaveService.updateLeave(leave); }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void supp_leave(@PathVariable("id") long id){leaveService.deleteLeave((int) id); }


//    @PutMapping("/add/{employee_id}/{leave_id}")
//    @ResponseBody
//    public void add_New_Leave_for_employee( @RequestBody @PathVariable("employee_id") long employee_id,@PathVariable("leave_id") Integer leave_id ) {
//        leaveService.add_Leave_To_employee(employee_id, leave_id);
//
//    }



}
