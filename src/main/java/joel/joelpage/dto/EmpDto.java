package joel.joelpage.dto;

import jakarta.persistence.Enumerated;
import joel.joelpage.entity.EmpGender;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EmpDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime workDate;
    @Enumerated
    private EmpGender gender;
    private boolean isWorked;
    private int workCount;
    private int pay;
    private int age;
    private String empDesc;

    @Builder
    public EmpDto(Long id, String name, String email, String phone, LocalDateTime workDate, EmpGender gender, boolean isWorked, int workCount, int pay, int age, String empDesc) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.workDate = workDate;
        this.gender = gender;
        this.isWorked = isWorked;
        this.workCount = workCount;
        this.pay = pay;
        this.age = age;
        this.empDesc = empDesc;
    }
}
