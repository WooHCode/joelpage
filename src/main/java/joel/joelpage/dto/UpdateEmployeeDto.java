package joel.joelpage.dto;

import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateEmployeeDto {
    private String empName;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;
}
