package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false)
    private Long id;

    @Column(name = "s_item_name")
    private String saleItemName;

    @Column(name = "s_item_price")
    private String saleItemPrice;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "sale_count")
    private int saleCount;

    @Column(name = "item_total_sale")
    private int itemTotalSale;

    @Builder
    public Sale(Long id, String saleItemName, String saleItemPrice, LocalDate saleDate, int saleCount, int itemTotalSale) {
        this.id = id;
        this.saleItemName = saleItemName;
        this.saleItemPrice = saleItemPrice;
        this.saleDate = saleDate;
        this.saleCount = saleCount;
        this.itemTotalSale = itemTotalSale;
    }
    public void toEntity(String saleItemName, String saleItemPrice, LocalDate saleDate, int saleCount, int itemTotalSale) {
        this.saleItemName = saleItemName;
        this.saleItemPrice = saleItemPrice;
        this.saleDate = saleDate;
        this.saleCount = saleCount;
        this.itemTotalSale = itemTotalSale;
    }
}
