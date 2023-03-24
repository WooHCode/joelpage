package joel.joelpage.config;

import jakarta.annotation.PostConstruct;
import joel.joelpage.dto.EmpDto;
import joel.joelpage.entity.EmpGender;
import joel.joelpage.service.EmployeeService;
import joel.joelpage.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class dbInit {

    private final EmployeeService employeeService;

    private final SaleService saleService;

    //@PostConstruct
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
        /*for (int i = 1; i < 8; i++) {
            UpdateSaleDto dto = UpdateSaleDto.builder()
                    .saleItemName("아메리카노" + i)
                    .saleItemCode(ItemCode.COFFEE)
                    .saleDate(LocalDateTime.now().minusDays(i))
                    .saleCount(25)
                    .itemTotalSale(300)
                    .saleItemPrice(2500)
                    .build();
            saleService.saveOneSaleByDto(dto);
        }*/
    }
}
