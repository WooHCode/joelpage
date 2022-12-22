package joel.joelpage.controller;

import jakarta.validation.Valid;
import joel.joelpage.dto.ItemDto;
import joel.joelpage.entity.Item;
import joel.joelpage.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createItemForm(Model model) {
        model.addAttribute("form", new ItemDto());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@Valid ItemDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "items/createItemForm";
        }
        Item item = new Item();
        item.toEntity(dto.getName(),dto.getPrice(),dto.getImgPath(),dto.getItemDesc());
        itemService.saveItem(item);
        return "itemList";
    }


    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }
}
