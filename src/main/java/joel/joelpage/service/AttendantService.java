package joel.joelpage.service;

import joel.joelpage.dto.AttendantInfoDto;
import joel.joelpage.entity.Attendance;
import joel.joelpage.entity.Employee;
import joel.joelpage.repository.AttendantRepository;
import joel.joelpage.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendantService {
    private final AttendantRepository attendantRepository;
    private final EmployeeRepository employeeRepository;

    public Attendance findAttendance(Long attId) {
        return attendantRepository.findById(attId).get();
    }
    public List<Attendance> findAllAttendance(Long empId) {
        List<Attendance> findAtt = attendantRepository.findByEmployeeIdOrderByAttendTimeDesc(empId);
        int dayOfMonth = LocalDateTime.now().getDayOfMonth();
        return findAtt.stream().filter(a -> a.getAttendTime().getDayOfMonth() == dayOfMonth).collect(Collectors.toList());
    }

    @Transactional
    public Long saveWork(Long empId) {
        Employee employee = employeeRepository.findById(empId).get();
        LocalDateTime time = LocalDateTime.now();
        employee.updateWorkDate(time);
        Attendance attendance = new Attendance();
        attendance.toAttendTime(time, employee);
        Attendance att = attendantRepository.save(attendance);
        employee.saveAtt(attendance);
        return att.getId();

    }
}
