package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Employee extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false)
    private Long id;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_gender")
    @Enumerated(EnumType.STRING)
    private EmpGender empGender;

    @Column(name = "is_worked")
    private boolean isWorked;

    @Column(name = "emp_work_count")
    private int empWorkCount;
    @Column(name = "emp_pay")
    private int empPay;

    @Column(name = "emp_age")
    private int empAge;

    @Column(name = "emp_description")
    private String empDescription;

    public Employee(Long id, String empName,EmpGender gender, boolean isWorked ,int empWorkCount, int empPay, int empAge, String empDescription) {
        this.id = id;
        this.empName = empName;
        this.empGender = gender;
        this.isWorked = isWorked;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }

    public void toEntity(String empName,EmpGender gender, int empWorkCount, int empPay, int empAge, String empDescription) {
        this.empName = empName;
        this.empGender = gender;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }
}
