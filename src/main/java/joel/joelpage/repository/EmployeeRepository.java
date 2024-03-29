package joel.joelpage.repository;

import joel.joelpage.entity.EmpGender;
import joel.joelpage.entity.Employee;
import joel.joelpage.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /** 전체 조회 후 내림 차순으로 정렬 */
    @Query("SELECT e FROM Employee e ORDER BY e.id ASC")
    List<Employee> findAllDesc();

    Page<Employee> findAll(Pageable pageable);

    Page<Employee> findByEmpGender(EmpGender empGender, Pageable pageable);

    Page<Employee> findByEmpName(String empName, Pageable pageable);

    Employee findByEmpName(String empName);

    @Query("SELECT e FROM Employee e WHERE e.loginMember.seq = :loginMemberSeq")
    Employee findByLoginMemberSeq(Long loginMemberSeq);

}
