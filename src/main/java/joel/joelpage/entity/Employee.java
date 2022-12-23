package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false)
    private Long id;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "work_date")
    private LocalDate workDate;

    @Column(name = "is_worked")
    private boolean isWorked;

    @Column(name = "emp_work_count")
    private int empWorkCount;
    @Column(name = "emp_pay")
    private int empPay;

    @Column(name = "emp_age")
    private String empAge;

    @Column(name = "emp_description")
    private String empDescription;

    public Employee(Long id, String empName, LocalDate workDate, boolean isWorked ,int empWorkCount, int empPay, String empAge, String empDescription) {
        this.id = id;
        this.empName = empName;
        this.workDate = workDate;
        this.isWorked = isWorked;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }

    public void toEntity(String empName, LocalDate workDate, int empWorkCount, int empPay, String empAge, String empDescription) {
        this.empName = empName;
        this.workDate = workDate;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }
}
