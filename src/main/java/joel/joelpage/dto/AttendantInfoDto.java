package joel.joelpage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendantInfoDto {
    private String empName;
    private int salary;
    private int workCount;
    private LocalDateTime workDate;
}
