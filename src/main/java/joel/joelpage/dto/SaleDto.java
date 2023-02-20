package joel.joelpage.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SaleDto {

    private Long id;

    private String saleItemName;

    private String saleItemPrice;

    private LocalDate saleDate;

    private int saleCount;

    private int itemTotalSale;
}
