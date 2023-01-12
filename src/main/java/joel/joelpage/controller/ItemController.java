package joel.joelpage.controller;

import jakarta.validation.Valid;
import joel.joelpage.dto.ItemDto;
import joel.joelpage.dto.UpdateItemDto;
import joel.joelpage.entity.Item;
import joel.joelpage.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/")
    public String index() {
        return "/home";
    }

    @GetMapping("/items/new")
    public String createItemForm(Model model) {
        model.addAttribute("form", new ItemDto());
        return "items/createItemForm";
    }
    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return "redirect:/items";
    }

    @GetMapping("/items/edit/{id}")
    public String updateItemForm(@PathVariable Long id, Model model) {
        UpdateItemDto updateItemDto = itemService.getUpdateItemDto(id);
        model.addAttribute("form", updateItemDto);
        return "items/updateItemForm";
    }

    @PostMapping("/items/new")
    public String createItem(@Valid ItemDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "items/createItemForm";
        }
        Item item = new Item();
        item.toEntity(dto.getName(),dto.getPrice(),dto.getImgPath(),dto.getItemDes(), dto.getItemCode());
        itemService.saveItem(item);
        return "redirect:/home";
    }

    @PostMapping("/items/edit")
    public String updateItem(UpdateItemDto dto) {
        itemService.updateItem(dto.getId(), dto);
        return "redirect:/home";
    }

}
