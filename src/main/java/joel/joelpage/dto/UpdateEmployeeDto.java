package joel.joelpage.dto;

import lombok.Getter;

@Getter
public class UpdateEmployeeDto {
    private String empName;
    private String gender;
    private int empWorkCount;
    private int empPay;
    private int empAge;
    private String empDescription;
}
