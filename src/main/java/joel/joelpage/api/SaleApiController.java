package joel.joelpage.api;

import joel.joelpage.dto.SaleDto;
import joel.joelpage.dto.SaveSaleDto;
import joel.joelpage.entity.ItemCode;
import joel.joelpage.entity.Sale;
import joel.joelpage.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://joeladmin.store", "http://ec2-52-79-168-230.ap-northeast-2.compute.amazonaws.com:8080"})
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
        Map<LocalDate, Integer> allByWeekDate = saleService.findAllByWeekDate();
        return allByWeekDate;
    }
    @GetMapping("/api/v2/sales")
    public Map<String,Integer> getMonthlySale() {
        return saleService.findAllByMonth();
    }

    @GetMapping("/api/v2/sales/{itemCode}")
    public Map<String,Integer> getSaleByItemCode(@PathVariable(value = "itemCode") ItemCode itemCode) {
       return saleService.findSaleByItemCode(itemCode);
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
    @PostMapping("/api/v1/sales")
    public void saveSales(@RequestBody List<SaveSaleDto> dto) {
        saleService.saveListSale(dto);
    }

}
