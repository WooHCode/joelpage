package joel.joelpage.api;

import joel.joelpage.dto.SaleDto;
import joel.joelpage.entity.Sale;
import joel.joelpage.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SaleApiController {
    private final SaleService saleService;

    @GetMapping("/api/v1/sale")
    public List<SaleDto> getAllSales() {
        List<Sale> allSales = saleService.findAllSales();
        List<SaleDto> saleDto = allSales.stream().map(s ->
                new SaleDto(s.getId(), s.getSaleItemName(), s.getSaleItemPrice(), s.getSaleDate(),
                        s.getSaleItemCode(), s.getSaleCount(), s.getItemTotalSale())).collect(Collectors.toList());
        return saleDto;
    }

    @GetMapping("/api/v1/sale/{id}")
    public SaleDto getOneSale(@PathVariable(value = "id") Long id) {
        Sale sale = saleService.findOneSaleById(id);
        SaleDto saleDto = SaleDto.builder()
                .id(sale.getId())
                .saleDate(sale.getSaleDate())
                .saleCount(sale.getSaleCount())
                .itemTotalSale(sale.getItemTotalSale())
                .saleItemPrice(sale.getSaleItemPrice())
                .saleItemCode(sale.getSaleItemCode())
                .saleItemName(sale.getSaleItemName())
                .build();
        return saleDto;
    }
}
