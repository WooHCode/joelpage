package joel.joelpage.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import joel.joelpage.entity.EmpGender;
import lombok.Data;
import lombok.Getter;

@Data
public class EmpDto {
    private Long id;
    private String empName;
    @Enumerated(EnumType.STRING)
    private EmpGender empGender;
    private boolean isWorked;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;
}