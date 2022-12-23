package joel.joelpage.repository;

import joel.joelpage.entity.Employee;
import joel.joelpage.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
