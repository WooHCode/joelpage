package joel.joelpage.service;

import joel.joelpage.dto.UpdateEmployeeDto;
import joel.joelpage.entity.EmpGender;
import joel.joelpage.entity.Employee;
import joel.joelpage.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private static final int payPerHour = 9000;

    /**
     * id 값으로 데이터 한건 조회
     */
    public Employee findOneEmp(Long id) {
        return employeeRepository.findById(id).get();

    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Page<Employee> findAllWithPage(Pageable pageable) {
        Page<Employee> map = employeeRepository.findAll(pageable).map(employee -> employee);
        return map;
    }

    public Page<Employee> findByGenderPage(EmpGender gender, Pageable pageable) {
        Page<Employee> findEmpByGender = employeeRepository.findByEmpGender(gender, pageable);
        return findEmpByGender;
    }

    public Page<Employee> findByEmpNamePage(String empName, Pageable pageable) {
        return employeeRepository.findByEmpName(empName, pageable);
    }

    /**
     * 데이터 전체 조회
     */
    public List<Employee> findAllEmp() {
        return employeeRepository.findAllDesc();
    }

    /**
     * 데이터 dto에 id로 한건 조회 후 저장
     */
    public UpdateEmployeeDto getUpdateEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        return UpdateEmployeeDto.builder()
                .id(employee.getId())
                .empAge(employee.getEmpAge())
                .empEmail(employee.getEmpEmail())
                .empPhone(employee.getEmpPhone())
                .workDate(employee.getWorkDate())
                .empDescription(employee.getEmpDescription())
                .empName(employee.getEmpName())
                .gender(employee.getEmpGender())
                .empPay(employee.getEmpPay())
                .empWorkCount(employee.getEmpWorkCount())
                .build();
    }

    /**
     * 직원 데이터 한 건 저장
     */
    @Transactional
    public Long saveEmp(Employee employee) {
        Employee savedEmp = employeeRepository.save(employee);
        return savedEmp.getId();
    }

    /**
     * 직원 데이터 Object로 가져와서 전체 데이터 수정
     * 기존 직원데이터에 출근날짜와 dto에서 받아온 직원 날짜가 다르면(출근했다면) workCount를 1 올려서 update한다.
     */
    @Transactional
    public void updateEmp(UpdateEmployeeDto dto) {
        Employee employee = employeeRepository.findById(dto.getId()).get();
        int plusWorkCount = 0;
        int totalEmpPay = 0;
        if (employee.getWorkDate() != dto.getWorkDate()) {
            plusWorkCount = employee.getEmpWorkCount() + 1;
            totalEmpPay = plusWorkCount * payPerHour;
        }
        employee.toEntity(dto.getEmpName(), dto.getEmpPhone(), dto.getEmpEmail(), dto.getWorkDate(), dto.getEmpGender(), plusWorkCount, totalEmpPay, dto.getEmpAge(), dto.getEmpDescription());
    }

    /**
     * id로 한 건을 가져와 workDate가 변경되면 count 1증가
     */
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
