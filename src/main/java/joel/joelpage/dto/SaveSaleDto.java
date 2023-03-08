package joel.joelpage.dto;

import joel.joelpage.entity.ItemCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaveSaleDto {
    private String itemName;
    private int itemCount;
    private ItemCode itemCode;
    private int itemPrice;
    private LocalDate saleDate;

    @Builder
    public SaveSaleDto(String itemName, int itemCount, ItemCode itemCode, int itemPrice, LocalDate saleDate) {
        this.itemName = itemName;
        this.itemCount = itemCount;
        this.itemCode = itemCode;
        this.itemPrice = itemPrice;
        this.saleDate = saleDate;
    }
}
