package joel.joelpage.entity;

import java.time.LocalDate;

//Many to one
public class Sale {
    private Long id;
    private String itemName;
    private String itemPrice;
    private LocalDate saleDate;
    private int saleCount;
    private int itemTotalSale;
}
