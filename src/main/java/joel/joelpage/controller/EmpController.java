package joel.joelpage.controller;

import joel.joelpage.dto.EmpDto;
import joel.joelpage.dto.UpdateEmployeeDto;
import joel.joelpage.entity.Employee;
import joel.joelpage.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/emp/new")
    public String empCreate(Model model) {
        model.addAttribute("ems",new EmpDto());
        return "emp/createEmpForm";
    }

    @PostMapping("/emp/new")
    public String empCreateForm(EmpDto dto) {
        Employee employee = new Employee();
        employee.toEntity(dto.getEmpName(), dto.getEmpGender(),dto.getEmpWorkCount(),dto.getEmpPay(),dto.getEmpAge(), dto.getEmpDescription());
        employeeService.saveEmp(employee);
        return "redirect:/home";
    }

    @GetMapping("/emp/delete/{id}")
    private String empDelete(@PathVariable Long id) {
        employeeService.deleteEmp(id);
        return "redirect:/emp";
    }
    @GetMapping("/emp/edit/{id}")
    public String empEdit(@PathVariable Long id, Model model) {
        UpdateEmployeeDto dto = employeeService.getUpdateEmployee(id);
        model.addAttribute("form", dto);
        return "emp/updateEmpForm";
    }

    @PostMapping("/emp/edit")
    public String empEdit(Long id ,UpdateEmployeeDto dto) {
        employeeService.updateEmp(id, dto);
        return "redirect:/home";
    }
}
