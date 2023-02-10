package joel.joelpage.api;

import joel.joelpage.dto.EmpDto;
import joel.joelpage.dto.UpdateEmployeeDto;
import joel.joelpage.entity.EmpGender;
import joel.joelpage.entity.Employee;
import joel.joelpage.service.EmployeeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/api/v3/emp")
    public List<Employee> findPage(Pageable pageable) {
        List<Employee> employeeList = employeeService.findAllWithPage(pageable).getContent();
        return employeeList;
    }
    @GetMapping("/api/v3/emp/total")
    public List<Number> findPageCount(Pageable pageable) {
        long totalElements = employeeService.findAllWithPage(pageable).getTotalElements();
        int totalPages = employeeService.findAllWithPage(pageable).getTotalPages();
        List<Number> resList = new ArrayList<>();
        resList.add(0,totalPages);
        resList.add(1, totalElements);
        return resList;
    }

    @GetMapping("/empDetail/api/v1/emp/{searchName}")
    public EmpDto findOneEmpByName(@PathVariable(value = "searchName") String name) {
        Employee findEmp = employeeService.findByEmpName(name);
        EmpDto resultEmp = EmpDto.builder()
                .id(findEmp.getId())
                .name(findEmp.getEmpName())
                .email(findEmp.getEmpEmail())
                .phone(findEmp.getEmpPhone())
                .workDate(findEmp.getWorkDate())
                .gender(findEmp.getEmpGender())
                .isWorked(findEmp.isWorked())
                .workCount(findEmp.getEmpWorkCount())
                .pay(findEmp.getEmpPay())
                .age(findEmp.getEmpAge())
                .empDesc(findEmp.getEmpDescription())
                .build();
        return resultEmp;
    }

    @GetMapping("/api/v1/emp/search")
    public Page<Employee> findEmpWithEmpGender(@RequestParam EmpGender empGender, Pageable pageable) {
        Page<Employee> genderPage = employeeService.findByGenderPage(empGender, pageable);
        return genderPage;
    }

    @GetMapping("/api/v2/emp/search")
    public Page<Employee> findEmpWithEmpName(@RequestParam String empName, Pageable pageable) {
        return employeeService.findByEmpNamePage(empName, pageable);
    }

    @PostMapping("/api/v1/emp/save")
    public CreateEmpResponse saveEmp(@RequestBody Employee employee) {
        Long id = employeeService.saveEmp(employee);
        return new CreateEmpResponse(id);
    }

    @PatchMapping("/api/v1/emp/update/{empId}")
    public HttpStatus updateEmp(@PathVariable("empId") Long id, @RequestBody UpdateEmployeeDto dto) {
        Employee findEmp = employeeService.findOneEmp(id);
        findEmp.toEntity(dto.getEmpName(),dto.getEmpPhone(),dto.getEmpEmail(),dto.getOffTime(),dto.getEmpGender(), dto.getEmpWorkCount(), dto.getEmpPay(), dto.getEmpAge(),dto.getEmpDescription());
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
