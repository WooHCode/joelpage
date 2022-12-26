package joel.joelpage.api;

import joel.joelpage.entity.Item;
import joel.joelpage.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    @GetMapping("/api/v1/items")
    public List<Item> findAllItems() {
        return itemService.findAllItems();
    }

}
