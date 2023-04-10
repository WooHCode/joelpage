package joel.joelpage.api;

import joel.joelpage.dto.*;
import joel.joelpage.entity.Attendance;
import joel.joelpage.entity.EmpGender;
import joel.joelpage.entity.Employee;
import joel.joelpage.service.AttendantService;
import joel.joelpage.service.EmployeeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://joeladmin.store", "http://ec2-52-79-168-230.ap-northeast-2.compute.amazonaws.com:8080"})
@RequiredArgsConstructor
public class EmpApiController {
    private final EmployeeService employeeService;
    private final AttendantService attendantService;

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
        return employeeService.findAllWithPage(pageable).getContent();
    }

    @GetMapping("/api/v4/emp/{loginId}")
    public EmpDto myPageEmpInfo(@PathVariable("loginId") Long loginId) {
        Employee findMember = employeeService.findByLoginMemberId(loginId);
        return new EmpDto(findMember.getId(),findMember.getEmpName(),findMember.getEmpEmail(),
                findMember.getEmpPhone(),findMember.getWorkDate(),findMember.getEmpGender(),
                findMember.isWorked(),findMember.getEmpWorkCount(),findMember.getEmpPay(),findMember.getEmpAge(),findMember.getEmpDescription());
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
    @GetMapping("/api/v5/emp/{loginId}")
    public List<AttendantInfoDto> findAllEmpWork(@PathVariable(value = "loginId") Long loginId) {
        Employee loginMember = employeeService.findByLoginMemberId(loginId);
        List<Attendance> attList = attendantService.findAllAttendance(loginMember.getId());
        return attList.stream().map(a -> new AttendantInfoDto
                (loginMember.getEmpName(), loginMember.getEmpPay(), loginMember.getEmpWorkCount(), a.getAttendTime())).collect(Collectors.toList());
    }
    @CrossOrigin(origins = {"http://joeladmin.store", "http://ec2-52-79-168-230.ap-northeast-2.compute.amazonaws.com:8080"})

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
        return employeeService.findByGenderPage(empGender, pageable);
    }

    @GetMapping("/api/v2/emp/search")
    public Page<Employee> findEmpWithEmpName(@RequestParam String empName, Pageable pageable) {
        return employeeService.findByEmpNamePage(empName, pageable);
    }

    @PostMapping("/api/v1/emp/save")
    public CreateEmpResponse saveEmp(@RequestBody EmpDto dto) {
        Long id = employeeService.saveEmp(dto);
        return new CreateEmpResponse(id);
    }
    @PostMapping("/api/v2/emp/save")
    public ResponseEntity saveEmpWithLoginInfo(@RequestBody EmpWithLoginInfoDto dto) {
        employeeService.saveEmpWithLoginInfo(dto.getEmpDto(),dto.getLoginInfoDto());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/api/v3/emp/work/{empId}")
    public ResponseEntity saveEmpWork(@PathVariable("empId") Long id) {
        Long attId = attendantService.saveWork(id);
        return new ResponseEntity<>(attId,HttpStatus.OK);
    }

    @PatchMapping("/api/v3/emp/work/{empId}")
    public ResponseEntity updateEmpWork(@PathVariable("empId") Long id, @RequestBody RequestWorkDto workDto) {
        Employee employee = employeeService.findByLoginMemberId(id);
        Attendance attendance = attendantService.findAttendance(workDto.getAttId());
        LocalDateTime attendTime = LocalDateTime.now();
        UpdateEmployeeDto dto = UpdateEmployeeDto.builder()
                .id(employee.getId())
                .empName(employee.getEmpName())
                .empPhone(employee.getEmpPhone())
                .empEmail(employee.getEmpEmail())
                .empEnterDate(employee.getEnterDate())
                .offTime(attendTime)
                .startWorkTime(employee.getWorkDate())
                .empGender(employee.getEmpGender())
                .empWorkCount(employee.getEmpWorkCount())
                .empPay(employee.getEmpPay())
                .empAge(employee.getEmpAge())
                .empDescription(employee.getEmpDescription())
                .build();
        employeeService.updateEmp(dto);
        attendance.AttendTimeChange(attendTime);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/api/v1/emp/update/{empId}")
    public HttpStatus updateEmp(@PathVariable("empId") Long id, @RequestBody UpdateEmployeeDto dto) {
        Employee findEmp = employeeService.findOneEmp(id);
        findEmp.toEntity(dto.getEmpName(),dto.getEmpPhone(),dto.getEmpEmail(),dto.getEmpEnterDate(),dto.getOffTime(),dto.getEmpGender(), dto.getEmpWorkCount(), dto.getEmpPay(), dto.getEmpAge(),dto.getEmpDescription());
        return HttpStatus.OK;
    }

    @PutMapping("/empDetail/api/v1/emp/update/{empId}")
    public HttpStatus updateEmpDetail(@PathVariable("empId") Long id, @RequestBody UpdateEmployeeDto dto) {
        employeeService.updateEmpById(id, dto);
        return HttpStatus.OK;
    }
    @PutMapping("/api/v2/emp/update/{empId}")
    public HttpStatus updateMyPageDetail(@PathVariable("empId") Long id, @RequestBody UpdateEmployeeDto dto) {
        employeeService.updateEmpById(id, dto);
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
