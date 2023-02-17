package joel.joelpage.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor // proxy 객체 생성을 위해 추가한다.
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", nullable = false)
    private Long id;

    @Column(name = "item_name", nullable = false, unique = true)
    private String name;

    @Column(name = "item_price", nullable = false)
    private int price;

    @Column(name = "item_image", nullable = false)
    private String imgPath;

    @Column(name = "item_description")
    private String itemDes;

    @Enumerated(EnumType.STRING)
    private ItemCode itemCode;

    @Builder
    public Item(Long id, String name, int price, String imgPath, String itemDes, ItemCode itemCode) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgPath = imgPath;
        this.itemDes = itemDes;
        this.itemCode = itemCode;
    }

    public void toEntity(String name, int price, String imgPath , String itemDes, ItemCode itemCode) {
        this.name = name;
        this.price = price;
        this.itemDes = itemDes;
        this.imgPath = imgPath;
        this.itemCode = itemCode;
    }
}
