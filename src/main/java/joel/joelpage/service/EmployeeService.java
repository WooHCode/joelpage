package joel.joelpage.service;

import joel.joelpage.dto.EmpDto;
import joel.joelpage.dto.LoginInfoDto;
import joel.joelpage.dto.UpdateEmployeeDto;
import joel.joelpage.entity.EmpGender;
import joel.joelpage.entity.Employee;
import joel.joelpage.entity.LoginMember;
import joel.joelpage.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        try {
            return employeeRepository.findAll();
        } catch (IllegalStateException e) {
            throw e;
        }

    }

    public Page<Employee> findAllWithPage(Pageable pageable) {
        Page<Employee> map = employeeRepository.findAll(pageable).map(employee -> employee);
        return map;
    }

    public Page<Employee> findByGenderPage(EmpGender gender, Pageable pageable) {
        Page<Employee> findEmpByGender = employeeRepository.findByEmpGender(gender, pageable);
        return findEmpByGender;
    }

    public Employee findByEmpName(String empName) {
        return employeeRepository.findByEmpName(empName);
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
                .empEnterDate(employee.getEnterDate())
                .empPhone(employee.getEmpPhone())
                .startWorkTime(employee.getWorkDate())
                .empDescription(employee.getEmpDescription())
                .empName(employee.getEmpName())
                .empGender(employee.getEmpGender())
                .empPay(employee.getEmpPay())
                .empWorkCount(employee.getEmpWorkCount())
                .build();
    }

    /**
     * 직원 데이터 한 건 저장
     */
    @Transactional
    public Long saveEmp(EmpDto dto) {
        Employee newEmp = new Employee(dto.getId(), dto.getName(), dto.getPhone(), dto.getEmail(), dto.getEnterDate(), dto.getWorkDate(),
                dto.getGender(), false, dto.getWorkCount(), dto.getPay(), dto.getAge(), dto.getEmpDesc());
        Employee savedEmp = employeeRepository.save(newEmp);
        return savedEmp.getId();
    }

    @Transactional
    public void saveEmpWithLoginInfo(EmpDto dto, LoginInfoDto infoDto) {
        Employee employee = new Employee();
        LoginMember loginMember = new LoginMember();
        loginMember.toEntity(infoDto.getInfoId(),infoDto.getInfoPw(),infoDto.getInfoCode());
        employee.toEmployeeEntity(dto.getName(),dto.getPhone(),dto.getEmail(),dto.getEnterDate(),dto.getWorkDate(),dto.getGender(),dto.getWorkCount(),
                dto.getPay(),dto.getAge(),dto.getEmpDesc(),loginMember);
        employeeRepository.save(employee);
    }

    /**
     * 직원 데이터 Object로 가져와서 전체 데이터 수정
     * 출근시간과 퇴근시간을 받아서 시간단위만 가져와 시급을 곱하여 엔티티에 저장
     */
    @Transactional
    public void updateEmp(UpdateEmployeeDto dto) {
        Employee employee = employeeRepository.findById(dto.getId()).get();
        int plusWorkCount;
        int totalEmpPay;
        Duration difference = workTimeDifference(employee, dto);
        plusWorkCount = employee.getEmpWorkCount() + 1;
        int diffWorkTime = ((int) difference.getSeconds() / 3600);
        // 하루가 지났다면 -24시간이 되어있기때문에 정확한 데이터를 위해 +24를 해주면 그 차이가 나온다.
        if (diffWorkTime < 0) diffWorkTime += 24;
        totalEmpPay = diffWorkTime * payPerHour;
        totalEmpPay = employee.getEmpPay() + totalEmpPay;
        employee.toEntity(dto.getEmpName(), dto.getEmpPhone(), dto.getEmpEmail(), dto.getEmpEnterDate(), dto.getOffTime(), dto.getEmpGender(), plusWorkCount, totalEmpPay, dto.getEmpAge(), dto.getEmpDescription());
    }

    /**
     * 직원 1명의 정보를 id값으로 가져온 후, dto로 받아온 데이터로 db데이터 수정
     * 리턴값 없음
     *
     * @param id
     * @param dto
     */
    @Transactional
    public void updateEmpById(Long id, UpdateEmployeeDto dto) {
        Employee findEmp = employeeRepository.findById(id).get();
        findEmp.toEntity(dto.getEmpName(), dto.getEmpPhone(), dto.getEmpEmail(), dto.getEmpEnterDate(), dto.getOffTime(), dto.getEmpGender(), dto.getEmpWorkCount(), dto.getEmpPay(), dto.getEmpAge(), dto.getEmpDescription());
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

    static Duration workTimeDifference(Employee employee, UpdateEmployeeDto dto) {
        LocalTime startTime;
        startTime = getStartTime(employee, dto);
        Duration difference = Duration.between(startTime, (dto.getOffTime()).toLocalTime());
        return difference;
    }

    private static LocalTime getStartTime(Employee employee, UpdateEmployeeDto dto) {
        LocalTime startTime;
        if (employee.getWorkDate() != dto.getOffTime() && dto.getStartWorkTime() == null) {
            startTime = employee.getWorkDate().toLocalTime();
        } else {
            startTime = dto.getStartWorkTime().toLocalTime();
        }
        return startTime;
    }

    public Employee findByLoginMemberId(Long id) {
        Employee employee = employeeRepository.findByLoginMemberSeq(id);
        return employee;
    }
}
