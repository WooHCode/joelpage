package joel.joelpage.service;

import joel.joelpage.dto.UpdateEmployeeDto;
import joel.joelpage.entity.Employee;
import joel.joelpage.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /** id 값으로 데이터 한건 조회 */
    public Employee findOneEmp(Long id) {
       return employeeRepository.findById(id).get();

    }
    /** 데이터 전체 조회 */
    public List<Employee> findAllEmp() {
        return employeeRepository.findAll();
    }
    /** 데이터 dto에 id로 한건 조회 후 저장 */
    public UpdateEmployeeDto getUpdateEmployee(Long id){
        Employee employee = employeeRepository.findById(id).get();
        return UpdateEmployeeDto.builder()
                .empAge(employee.getEmpAge())
                .empDescription(employee.getEmpDescription())
                .empName(employee.getEmpName())
                .gender(employee.getEmpGender())
                .empPay(employee.getEmpPay())
                .empWorkCount(employee.getEmpWorkCount())
                .build();
    }

    @Transactional
    public Long saveEmp(Employee employee) {
        Employee savedEmp = employeeRepository.save(employee);
        return savedEmp.getId();
    }

    @Transactional
    public void updateEmp(Long empId, UpdateEmployeeDto dto) {
        Employee employee = employeeRepository.findById(empId).get();
        employee.toEntity(dto.getEmpName(),dto.getGender(), dto.getEmpWorkCount(),dto.getEmpPay(), dto.getEmpAge(),dto.getEmpDescription());
    }

    @Transactional
    public void changeEmpWorkCount(Long empId, LocalDateTime workDate, int workCount) {
        Employee employee = employeeRepository.findById(empId).get();
    }

    @Transactional
    public void deleteEmp(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
    }

    static void isAdded() {

    }


}
