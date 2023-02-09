package joel.joelpage.dto;

import jakarta.persistence.Enumerated;
import joel.joelpage.entity.EmpGender;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmpDto {
    private Long id;
    private String empName;
    private String empEmail;
    private String empPhone;
    private LocalDateTime workDate;
    @Enumerated
    private EmpGender empGender;
    private boolean isWorked;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;
}
