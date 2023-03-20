package joel.joelpage.dto;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import joel.joelpage.entity.ItemCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    @NotEmpty(message = "상품명은 공백일 수 없습니다.")
    private String name;

    @Positive(message = "가격은 0이나 음수일 수 없습니다.")
    private int price;

    private String imgPath;
    private String itemDes;
    @Enumerated
    private ItemCode itemCode;


    public ItemDto(String name, int price, String imgPath, String itemDes, ItemCode itemCode) {
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.itemDes = itemDes;
        this.itemCode = itemCode;
    }

    public ItemDto() {
    }
}
