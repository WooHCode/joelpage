package joel.joelpage.config;

import jakarta.annotation.PostConstruct;
import joel.joelpage.dto.EmpDto;
import joel.joelpage.dto.UpdateItemDto;
import joel.joelpage.entity.EmpGender;
import joel.joelpage.entity.Employee;
import joel.joelpage.entity.ItemCode;
import joel.joelpage.service.EmployeeService;
import joel.joelpage.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class dbInit {

    private final EmployeeService employeeService;
    private final ItemService itemService;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 12; i++) {
            EmpDto dto = EmpDto.builder()
                    .email("이메일"+i)
                    .pay(0)
                    .phone("010-0000-00"+i)
                    .name("직원"+i)
                    .gender(EmpGender.M)
                    .workDate(LocalDateTime.now())
                    .empDesc("상세설명"+i)
                    .build();
            employeeService.saveEmp(dto);
        }
        for (int i = 0; i < 8; i++) {
            UpdateItemDto dto = UpdateItemDto.builder()
                    .itemName("상품"+i)
                    .price(1500)
                    .itemCode(ItemCode.ADE)
                    .imgPath("/img/result.jpg")
                    .itemDes("상품설명"+i)
                    .build();
            itemService.saveItemByDto(dto);
        }
    }
}
