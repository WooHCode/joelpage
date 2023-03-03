package joel.joelpage.service;

import joel.joelpage.dto.UpdateSaleDto;
import joel.joelpage.dto.WeekSaleDto;
import joel.joelpage.entity.ItemCode;
import joel.joelpage.entity.Sale;
import joel.joelpage.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SaleService {
    private final SaleRepository saleRepository;

    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    public Sale findOneSaleByDate() {
        return null;
    }

    public Sale findOneSaleById(Long id) {
        return saleRepository.findById(id).get();
    }

    public Map<LocalDate,Integer> findAllByWeekDate() {
        HashMap<LocalDate, Integer> resultMap = new HashMap<>();
        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();
        List<Sale> allBySaleDateBetween = saleRepository.findAllBySaleDateBetween(startDate, endDate);

        for (int i = 0; i < 8; i++) {
            int minusDate = i;
            int totalCount = 0;
            LocalDate insertDate = null;

            List<Sale> nowData = allBySaleDateBetween.stream()
                    .filter(n -> n.getSaleDate().isEqual(LocalDate.now().minusDays(minusDate))).toList();

            for (Sale sale : nowData) {
                int oneCount = sale.getSaleCount() * sale.getSaleItemPrice();
                insertDate = sale.getSaleDate();
                totalCount += oneCount;
            }
            resultMap.put(insertDate, totalCount);
            insertDate = null;
            totalCount = 0;
        }

        return resultMap;
    }

    public List<Sale> findSaleByItemCode(ItemCode itemCode) {
        return saleRepository.findBySaleItemCode(itemCode);
    }

    @Transactional
    public void saveOneSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Transactional
    public void saveOneSaleByDto(UpdateSaleDto dto) {
        Sale sale = Sale.builder()
                .saleItemName(dto.getSaleItemName())
                .itemTotalSale(dto.getItemTotalSale())
                .saleItemCode(dto.getSaleItemCode())
                .saleDate(dto.getSaleDate())
                .saleCount(dto.getSaleCount())
                .saleItemPrice(dto.getSaleItemPrice())
                .build();
        saleRepository.save(sale);
    }

    @Transactional
    public void updateOneSale(Long id, Sale sale) {
        Sale findSale = saleRepository.findById(id).get();
        findSale.toEntity(sale.getSaleItemName(), sale.getSaleItemPrice(), sale.getSaleDate(), sale.getSaleCount(), sale.getItemTotalSale());
    }

    @Transactional
    public void deleteOneSale(Long id) {
        Sale sale = saleRepository.findById(id).get();
        saleRepository.delete(sale);
    }
}
