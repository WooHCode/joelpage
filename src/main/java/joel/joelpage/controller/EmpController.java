package joel.joelpage.controller;

import joel.joelpage.entity.Employee;
import joel.joelpage.service.EmployeeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class EmpController {
    private final EmployeeService employeeService;

    @GetMapping("/emp")
    public String empList(Model model) {
        List<Employee> allEmp = employeeService.findAllEmp();
        model.addAttribute("ems", allEmp);
        return "emp/empList";
    }
}
