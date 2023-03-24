package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private int saleItemPrice;

    @Column(name = "s_item_code")
    @Enumerated(EnumType.STRING)
    private ItemCode saleItemCode;

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @Column(name = "sale_count")
    private int saleCount;

    @Column(name = "item_total_sale")
    private int itemTotalSale;

    @Builder
    public Sale(Long id, String saleItemName, int saleItemPrice, ItemCode saleItemCode, LocalDateTime saleDate, int saleCount, int itemTotalSale) {
        this.id = id;
        this.saleItemName = saleItemName;
        this.saleItemPrice = saleItemPrice;
        this.saleItemCode = saleItemCode;
        this.saleDate = saleDate;
        this.saleCount = saleCount;
        this.itemTotalSale = itemTotalSale;
    }
    public void toEntity(String saleItemName, int saleItemPrice, LocalDateTime saleDate, int saleCount, int itemTotalSale) {
        this.saleItemName = saleItemName;
        this.saleItemPrice = saleItemPrice;
        this.saleDate = saleDate;
        this.saleCount = saleCount;
        this.itemTotalSale = itemTotalSale;
    }
}
