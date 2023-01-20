package joel.joelpage.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import joel.joelpage.entity.EmpGender;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateEmployeeDto {
    private Long id;
    private String empName;
    @Enumerated(EnumType.STRING)
    private EmpGender gender;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;

    @Builder
    public UpdateEmployeeDto(String empName, EmpGender gender, int empWorkCount, int empPay, int empAge, String empDescription) {
        this.empName = empName;
        this.gender = gender;
        this.empWorkCount = empWorkCount;
        this.empPay = empPay;
        this.empAge = empAge;
        this.empDescription = empDescription;
    }
}
