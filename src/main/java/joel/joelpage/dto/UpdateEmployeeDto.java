package joel.joelpage.dto;

import joel.joelpage.entity.EmpGender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmployeeDto {
    private Long id;
    private String empName;
    private EmpGender empGender;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;

    public void setGender(EmpGender gender) {
        this.empGender = gender;
    }

    @Builder
    public UpdateEmployeeDto(String empName, EmpGender gender, int empWorkCount, int empPay, int empAge, String empDescription) {
        this.empName = empName;
        this.empGender = gender;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }
}
