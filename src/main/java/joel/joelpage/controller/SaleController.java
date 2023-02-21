package joel.joelpage.controller;

import joel.joelpage.dto.SaleDto;
import joel.joelpage.entity.Sale;
import joel.joelpage.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @GetMapping("/sales")
    public String saleList(Model model) {
        List<Sale> allSales = saleService.findAllSales();
        model.addAttribute("form",allSales);
        return "sale/saleList";
    }

    @GetMapping("/sale/new")
    public String createSale(Model model) {
        model.addAttribute("form", new SaleDto());
        return "sale/createSaleForm";
    }

    @PostMapping("/sale/new")
    public String createSale(@ModelAttribute SaleDto dto) {
        Sale sale = Sale.builder()
                .saleItemName(dto.getSaleItemName())
                .saleItemPrice(dto.getSaleItemPrice())
                .saleCount(dto.getSaleCount())
                .saleDate(dto.getSaleDate())
                .saleItemCode(dto.getSaleItemCode())
                .itemTotalSale(dto.getItemTotalSale())
                .build();
        saleService.saveOneSale(sale);
        return "redirect:/home";
    }
}
