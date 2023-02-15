package joel.joelpage.controller;

import joel.joelpage.dto.EmpDto;
import joel.joelpage.dto.UpdateEmployeeDto;
import joel.joelpage.entity.Employee;
import joel.joelpage.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    // ems 폼으로 받아 createform으로 전달
    @GetMapping("/emp/new")
    public String empCreate(Model model) {
        model.addAttribute("ems",new EmpDto());
        return "emp/createEmpForm";
    }

    @PostMapping("/emp/new")
    public String empCreateForm(EmpDto dto) {
        employeeService.saveEmp(dto);
        return "redirect:/home";
    }
    //한건 삭제
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
    //한 건 수정
    @PostMapping("/emp/edit")
    public String empEdit(@ModelAttribute UpdateEmployeeDto dto) {
        employeeService.updateEmp(dto);
        return "redirect:/home";
    }
}
