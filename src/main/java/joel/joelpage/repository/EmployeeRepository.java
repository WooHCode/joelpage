package joel.joelpage.repository;

import joel.joelpage.entity.Employee;
import joel.joelpage.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /** 전체 조회 후 내림 차순으로 정렬 */
    @Query("SELECT e FROM Employee e ORDER BY e.id ASC")
    List<Employee> findAllDesc();

    Page<Employee> findAll(Pageable pageable);
}
