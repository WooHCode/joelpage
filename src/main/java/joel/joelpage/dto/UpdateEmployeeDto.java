package joel.joelpage.dto;

import joel.joelpage.entity.EmpGender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeDto {
    private Long id;
    private String empName;
    private String empPhone;
    private String empEmail;
    private LocalDateTime workDate;
    private EmpGender empGender;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;

    public void setGender(EmpGender gender) {
        this.empGender = gender;
    }

    @Builder
    public UpdateEmployeeDto(Long id, String empName, EmpGender gender, int empWorkCount, int empPay, int empAge, String empDescription) {
        this.id = id;
        this.empName = empName;
        this.empGender = gender;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }
}
