package joel.joelpage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Employee extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id", nullable = false)
    private Long id;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "emp_phone")
    private String empPhone;

    @Column(name = "emp_email")
    private String empEmail;

    @Column(name = "emp_enter")
    private LocalDate enterDate;

    @Column(name = "work_date")
    private LocalDateTime workDate;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "login_seq")
    @JsonIgnore // 재귀 호출 방지
    private LoginMember loginMember;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Attendance> attendance;


    public Employee(Long id, String empName, String empPhone, String empEmail, LocalDate enterDate, LocalDateTime workDate, EmpGender empGender, boolean isWorked, int empWorkCount, int empPay, int empAge, String empDescription) {
        this.id = id;
        this.empName = empName;
        this.empPhone = empPhone;
        this.empEmail = empEmail;
        this.enterDate = enterDate;
        this.workDate = workDate;
        this.empGender = empGender;
        this.isWorked = isWorked;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }

    public void toEntity(String empName, String empPhone, String empEmail, LocalDate enterDate, LocalDateTime workDate, EmpGender gender, int empWorkCount, int empPay, int empAge, String empDescription) {
        this.empName = empName;
        this.empPhone = empPhone;
        this.empEmail = empEmail;
        this.enterDate = enterDate;
        this.workDate = workDate;
        this.empGender = gender;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }

    public void toEmployeeEntity(String empName, String empPhone, String empEmail, LocalDate enterDate, LocalDateTime workDate, EmpGender gender, int empWorkCount, int empPay, int empAge, String empDescription, LoginMember loginMember) {
        this.empName = empName;
        this.empPhone = empPhone;
        this.empEmail = empEmail;
        this.enterDate = enterDate;
        this.workDate = workDate;
        this.empGender = gender;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
        this.loginMember = loginMember;
    }

    public void saveAtt(Attendance attendance) {
        this.attendance.add(attendance);
        this.isWorked = true;
    }

    public void updateWorkDate(LocalDateTime time) {
        this.workDate = time;
    }
}
