package joel.joelpage.dto;

import joel.joelpage.entity.ItemCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleItemDto {
    private String itemName;
    private int itemPrice;
    private ItemCode itemCode;


    @Builder
    public SaleItemDto(String itemName, int itemPrice, ItemCode itemCode) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCode = itemCode;
    }
}
