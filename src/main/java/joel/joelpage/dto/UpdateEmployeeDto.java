package joel.joelpage.dto;

import joel.joelpage.entity.EmpGender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeDto {
    private Long id;
    private String empName;
    private String empPhone;
    private String empEmail;

    private LocalDate empEnterDate;

    private LocalDateTime offTime;
    private LocalDateTime startWorkTime;
    private EmpGender empGender;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;

    public void setGender(EmpGender gender) {
        this.empGender = gender;
    }

    @Builder
    public UpdateEmployeeDto(Long id, String empName, String empPhone, String empEmail, LocalDate empEnterDate, LocalDateTime offTime, LocalDateTime startWorkTime, EmpGender empGender, int empWorkCount, int empPay, int empAge, String empDescription) {
        this.id = id;
        this.empName = empName;
        this.empPhone = empPhone;
        this.empEmail = empEmail;
        this.empEnterDate = empEnterDate;
        this.offTime = offTime;
        this.startWorkTime = startWorkTime;
        this.empGender = empGender;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }
}
