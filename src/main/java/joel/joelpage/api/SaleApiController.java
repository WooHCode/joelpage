package joel.joelpage.api;

import joel.joelpage.dto.SaleDto;
import joel.joelpage.dto.WeekSaleDto;
import joel.joelpage.entity.Sale;
import joel.joelpage.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SaleApiController {
    private final SaleService saleService;

    @GetMapping("/api/v1/sale")
    public List<SaleDto> getAllSales() {
        List<Sale> allSales = saleService.findAllSales();
        return allSales.stream().map(s ->
                new SaleDto(s.getId(), s.getSaleItemName(), s.getSaleItemPrice(), s.getSaleDate(),
                        s.getSaleItemCode(), s.getSaleCount(), s.getItemTotalSale())).collect(Collectors.toList());
    }

    @GetMapping("/api/v1/sales")
    public Map<LocalDate, Integer> getWeeklySale() {
        return saleService.findAllByWeekDate();
    }
    @GetMapping("/api/v2/sales/")
    public Map<String,Integer> getMonthlySale() {
        return saleService.findAllByMonth();
    }


    @GetMapping("/api/v1/sale/{id}")
    public SaleDto getOneSale(@PathVariable(value = "id") Long id) {
        Sale sale = saleService.findOneSaleById(id);
        return SaleDto.builder()
                .id(sale.getId())
                .saleDate(sale.getSaleDate())
                .saleCount(sale.getSaleCount())
                .itemTotalSale(sale.getItemTotalSale())
                .saleItemPrice(sale.getSaleItemPrice())
                .saleItemCode(sale.getSaleItemCode())
                .saleItemName(sale.getSaleItemName())
                .build();
    }
}
