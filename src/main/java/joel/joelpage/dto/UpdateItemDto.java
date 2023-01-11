package joel.joelpage.dto;

import joel.joelpage.entity.ItemCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateItemDto {
    private String itemName;
    private int price;
    private String imgPath;
    private String itemDes;
    private ItemCode itemCode;

}
