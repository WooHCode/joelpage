package joel.joelpage.repository;

import joel.joelpage.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendantRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEmployeeIdOrderByAttendTimeDesc(Long empId);
}
