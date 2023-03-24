package joel.joelpage.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WeekSaleDto {
    public LocalDateTime weekDate;
    public int totalSale;

    @Builder
    public WeekSaleDto(LocalDateTime weekDate, int totalSale) {
        this.weekDate = weekDate;
        this.totalSale = totalSale;
    }
}
