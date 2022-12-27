package joel.joelpage.api;

import joel.joelpage.dto.ItemDto;
import joel.joelpage.entity.Item;
import joel.joelpage.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    @GetMapping("/api/v1/items")
    public List<Item> findAllItems() {
        return itemService.findAllItems();
    }

    @GetMapping("/api/v2/items")
    public Result findAllItems2() {
        List<Item> allItems = itemService.findAllItems();
        List<ItemDto> collect = allItems.stream()
                .map(i -> new ItemDto(i.getName()))
                .collect(Collectors.toList());
        return new Result(collect);
    }


    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ItemDto{
        private String name;
    }
}
