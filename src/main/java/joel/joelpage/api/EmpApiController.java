package joel.joelpage.api;

import joel.joelpage.entity.Employee;
import joel.joelpage.service.EmployeeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @Data
    static class CreateEmpResponse {
        public Long id;

        public CreateEmpResponse(Long id) {
            this.id = id;
        }
    }
}
