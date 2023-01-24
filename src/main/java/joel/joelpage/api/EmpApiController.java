package joel.joelpage.api;

import joel.joelpage.dto.UpdateEmployeeDto;
import joel.joelpage.dto.UpdateItemDto;
import joel.joelpage.entity.Employee;
import joel.joelpage.service.EmployeeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmpApiController {
    private final EmployeeService employeeService;

    @GetMapping("/api/v1/emp")
    public List<Employee> findAllEmp(){
        return employeeService.findAllEmp();
    }
    @GetMapping("/api/v2/emp")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
    @PostMapping("/api/v1/emp/save")
    public CreateEmpResponse saveEmp(@RequestBody Employee employee) {
        Long id = employeeService.saveEmp(employee);
        return new CreateEmpResponse(id);
    }

    @PatchMapping("/api/v1/emp/update/{empId}")
    public HttpStatus updateEmp(@PathVariable("empId") Long id, @RequestBody UpdateEmployeeDto dto) {
        Employee findEmp = employeeService.findOneEmp(id);
        findEmp.toEntity(dto.getEmpName(),dto.getEmpGender(), dto.getEmpWorkCount(), dto.getEmpPay(), dto.getEmpAge(),dto.getEmpDescription());
        return HttpStatus.OK;
    }

    @DeleteMapping("/api/v1/emp/delete/{empId}")
    public HttpStatus deleteEmp(@PathVariable("empId") Long id) {
        employeeService.deleteEmp(id);
        return HttpStatus.OK;
    }

    @Data
    static class CreateEmpResponse {
        public Long id;

        public CreateEmpResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class DeleteEmpResponse {
        public Long id;

        public DeleteEmpResponse(Long id) {
            this.id = id;
        }
    }
}
