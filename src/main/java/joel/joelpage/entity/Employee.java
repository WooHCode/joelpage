package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "work_date")
    private LocalDateTime workDate;

    @Column(name = "emp_work_count")
    private int empWorkCount;
    @Column(name = "emp_pay")
    private int empPay;

    @Column(name = "emp_age")
    private String empAge;

    @Column(name = "emp_description")
    private String empDescription;

    public Employee(Long id, String empName, LocalDateTime workDate, int empWorkCount, int empPay, String empAge, String empDescription) {
        this.id = id;
        this.empName = empName;
        this.workDate = workDate;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }
}
