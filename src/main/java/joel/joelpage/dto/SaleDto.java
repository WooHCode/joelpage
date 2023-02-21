package joel.joelpage.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import joel.joelpage.entity.ItemCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SaleDto {

    private Long id;

    private String saleItemName;

    private int saleItemPrice;

    private LocalDate saleDate;

    @Enumerated(EnumType.STRING)
    private ItemCode saleItemCode;

    private int saleCount;

    private int itemTotalSale;

    @Builder

    public SaleDto(Long id, String saleItemName, int saleItemPrice, LocalDate saleDate, ItemCode saleItemCode, int saleCount, int itemTotalSale) {
        this.id = id;
        this.saleItemName = saleItemName;
        this.saleItemPrice = saleItemPrice;
        this.saleDate = saleDate;
        this.saleItemCode = saleItemCode;
        this.saleCount = saleCount;
        this.itemTotalSale = itemTotalSale;
    }
}
