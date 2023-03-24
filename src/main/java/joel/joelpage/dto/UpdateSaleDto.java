package joel.joelpage.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import joel.joelpage.entity.ItemCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSaleDto {

    private String saleItemName;

    private int saleItemPrice;

    private LocalDateTime saleDate;

    @Enumerated(EnumType.STRING)
    private ItemCode saleItemCode;

    private int saleCount;

    private int itemTotalSale;

    @Builder
    public UpdateSaleDto( String saleItemName, int saleItemPrice, LocalDateTime saleDate, ItemCode saleItemCode, int saleCount, int itemTotalSale) {
        this.saleItemName = saleItemName;
        this.saleItemPrice = saleItemPrice;
        this.saleDate = saleDate;
        this.saleItemCode = saleItemCode;
        this.saleCount = saleCount;
        this.itemTotalSale = itemTotalSale;
    }
}
