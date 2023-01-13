package joel.joelpage.api;

import joel.joelpage.dto.ItemDto;
import joel.joelpage.dto.UpdateItemDto;
import joel.joelpage.entity.Item;
import joel.joelpage.entity.ItemCode;
import joel.joelpage.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    //엔티티 스펙을 노출한 상품 한건 조회
    @GetMapping("api/v1/items/{itemId}")
    public Item findOneItem(@PathVariable("itemId") Long id) {
        return itemService.findOneItem(id);
    }

    @GetMapping("/api/v3/items")
    public Result findItemPages(Pageable pageable) {
        List<Item> items = itemService.findAllItemPages(pageable).getContent();
        List<ItemDto> collect = items.stream().map(i -> new ItemDto(i.getId(), i.getName(), i.getPrice(), i.getImgPath(), i.getItemDes(), i.getItemCode()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("/api/v3/items/count")
    public List<Object> findTotalPageCountAndTotalElement(Pageable pageable) {
        int totalPages = itemService.findAllItemPages(pageable).getTotalPages();
        long totalElements = itemService.findAllItemPages(pageable).getTotalElements();
        List<Object> list = new ArrayList<>();
        list.add(0,totalPages);
        list.add(1,totalElements);
        return list;
    }

    //엔티티 스펙을 노출한 상품 한건 조회
    @GetMapping("/api/v1/items/{itemId}")
    public Item findOneItem(@PathVariable("itemId") Long id) {
        return itemService.findOneItem(id);
    }

    @GetMapping("/api/v2/items/{itemName}")
    public Item findOneByName(@PathVariable("itemName") String name) {
       return itemService.findOneItemByName(name);
    }

    @GetMapping("/api/v1/search/")
    public Item findOneItemByName(@RequestParam String name) {
        return itemService.findOneItemByName(name);
    }

    @GetMapping("/api/v2/search")
    public List<Item> findItemListLike(@RequestParam String likeName) {
        return itemService.findItemsByNameLike(likeName);
    }

    /**
     * 이름으로 조회 후 페이지네이션 값을 전달
     * 페이지 자료형에 기본적으로 totalElements와 totalPages를 제공하지만 api에서 편하게 사용하기 위해서 직접 제공한다.
     */
    @GetMapping("/api/v3/search")
    public List<Object> findItemListPage(@RequestParam String likeName, Pageable pageable) {
        Page<Item> itemPage = itemService.findItemsByNameLikePage(likeName, pageable);
        long totalElements = itemService.findItemsByNameLikePage(likeName, pageable).getTotalElements();
        int totalPages = itemService.findItemsByNameLikePage(likeName, pageable).getTotalPages();
        List<Object> resultList = new ArrayList<>();
        resultList.add(0,itemPage);
        resultList.add(1,totalElements);
        resultList.add(2,totalPages);
        return resultList;
    }

    //상품저장
    @PostMapping("/api/v1/items")
    public CreateItemResponse saveItemV1(@RequestBody Item item) {
        Long id = itemService.saveItem(item);
        return new CreateItemResponse(id);
    }
    //상품id를 파라미터로 한건 조회 후 수정
    @PostMapping("/api/v1/items/{itemId}")
    public UpdateItemResponse updateItemV1(@PathVariable("itemId") Long itemId, @RequestBody UpdateItemDto dto){
        Long id = itemService.updateItem(itemId, dto);
        return new UpdateItemResponse(id);
    }

    //상품 이름을 파라미터로 한건 조회 후 수정
    @PatchMapping("/api/v2/items/{itemName}")
    public UpdateItemResponseName updateItemV2(@PathVariable(name = "itemName") String name, @RequestBody UpdateItemDto dto) {
        String findName = itemService.updateItemByName(name, dto);
        return new UpdateItemResponseName(findName);
    }

    //id를 파라미터로 상품 1건 삭제
    @DeleteMapping("/api/v1/items/{itemId}")
    public void removeItem(@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    // 필요한 데이터만 리턴 가능
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

    @Data
    static class UpdateItemResponse {
        private Long id;

        public UpdateItemResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class UpdateItemResponseName {
        private String name;

        public UpdateItemResponseName(String name) {
            this.name = name;
        }
    }
}
