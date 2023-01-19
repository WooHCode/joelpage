package joel.joelpage.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import joel.joelpage.entity.EmpGender;
import lombok.Getter;

@Getter
public class UpdateEmployeeDto {
    private String empName;
    @Enumerated(EnumType.STRING)
    private EmpGender gender;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;
}
