package joel.joelpage.dto;

import joel.joelpage.entity.Item;
import joel.joelpage.entity.ItemCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateItemDto {
    private Long id;
    private String itemName;
    private int price;
    private String imgPath;
    private String itemDes;
    private ItemCode itemCode;

    public Item toEntity() {
        Item build = Item.builder()
                .id(id)
                .name(itemName)
                .price(price)
                .imgPath(imgPath)
                .itemDes(itemDes)
                .itemCode(itemCode)
                .build();
        return build;
    }

    @Builder
    public UpdateItemDto(Long id, String itemName, int price, String imgPath, String itemDes, ItemCode itemCode) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.imgPath = imgPath;
        this.itemDes = itemDes;
        this.itemCode = itemCode;
    }
}
