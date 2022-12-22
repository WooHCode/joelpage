package joel.joelpage.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    @NotEmpty(message = "상품명은 공백일 수 없습니다.")
    private String name;

    @NotEmpty(message = "가격은 공백일 수 없습니다.")
    private int price;

    private String imgPath;
    private String itemDesc;
}
