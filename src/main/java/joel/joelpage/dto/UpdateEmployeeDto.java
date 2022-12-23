package joel.joelpage.dto;

import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateEmployeeDto {
    private String empName;
    private LocalDateTime workDate;
    private int empWorkCount;
    private int empPay;
    private String empAge;
    private String empDescription;
}
