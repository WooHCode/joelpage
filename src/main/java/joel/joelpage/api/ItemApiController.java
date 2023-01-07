package joel.joelpage.api;

import joel.joelpage.dto.ItemDto;
import joel.joelpage.entity.Item;
import joel.joelpage.entity.ItemCode;
import joel.joelpage.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    //엔티티 스펙을 노출한 전체 상품조회
    @GetMapping("/api/v1/items")
    public List<Item> findAllItems() {
        return itemService.findAllItems();
    }

    //엔티티 스펙을 노출하지않고 내부클래스를 참조하여 전체상품조회
    @GetMapping("/api/v2/items")
    public Result findAllItems2() {
        List<Item> allItems = itemService.findAllItems();
        List<ItemDto> collect = allItems.stream()
                .map(i -> new ItemDto(i.getId(),i.getName(),i.getPrice(),i.getImgPath(),i.getItemDes(),i.getItemCode()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @PostMapping("/api/v1/items")
    public CreateItemResponse saveItemV1(@RequestBody Item item) {
        Long id = itemService.saveItem(item);
        return new CreateItemResponse(id);
    }
    @DeleteMapping("/api/v1/items/{itemId}")
    public void removeItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ItemDto{
        private Long id;
        private String name;
        private int price;
        private String image;
        private String description;
        private ItemCode itemCode;

    }

    @Data
    static class CreateItemResponse {
        private Long id;
        public CreateItemResponse(Long id) {
            this.id = id;
        }
    }
}
