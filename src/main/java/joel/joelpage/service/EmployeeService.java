package joel.joelpage.service;

import joel.joelpage.entity.Employee;
import joel.joelpage.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Long findOneEmp(Employee employee) {
        Employee emp = employeeRepository.findById(employee.getId()).get();
        return emp.getId();
    }

    public List<Employee> findAllEmp() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Long saveEmp(Employee employee) {
        Employee savedEmp = employeeRepository.save(employee);
        return savedEmp.getId();
    }

    @Transactional
    public void updateEmp() {

    }

}
