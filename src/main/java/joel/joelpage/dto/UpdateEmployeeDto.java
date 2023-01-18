package joel.joelpage.dto;

import jakarta.persistence.Column;
import joel.joelpage.entity.Gender;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UpdateEmployeeDto {
    private String empName;
    private Gender gender;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;
}
