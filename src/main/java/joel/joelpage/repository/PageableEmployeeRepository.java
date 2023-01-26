package joel.joelpage.repository;

import joel.joelpage.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageableEmployeeRepository extends JpaRepository<Employee,Long> {
    Page<Employee> findAll(Pageable pageable);
}
