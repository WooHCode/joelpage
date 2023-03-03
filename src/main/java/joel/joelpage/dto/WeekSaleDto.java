package joel.joelpage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeekSaleDto {
    public LocalDate weekDate;
    public int totalSale;

    @Builder
    public WeekSaleDto(LocalDate weekDate, int totalSale) {
        this.weekDate = weekDate;
        this.totalSale = totalSale;
    }
}
