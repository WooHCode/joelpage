package joel.joelpage.service;


import joel.joelpage.dto.SaveSaleDto;
import joel.joelpage.dto.UpdateSaleDto;
import joel.joelpage.entity.ItemCode;
import joel.joelpage.entity.Sale;
import joel.joelpage.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


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

    public Map<String, Integer> findAllByMonth() {
        HashMap<String, Integer> resultMap = new HashMap<>();
        LocalDate startDate = LocalDate.now().withMonth(1).withDayOfMonth(1); // 올해의 1월 1일
        LocalDate endDate = startDate.plusYears(1); // 올해의 12월 31일

        List<Sale> saleList = saleRepository.findAllBySaleDateBetween(startDate, endDate);

        for (int i = 1; i < 13; i++) {
            String finalI = String.valueOf(i);
            int insertValue = 0;
            List<Sale> collect = saleList.stream().filter(s -> String.valueOf(s.getSaleDate()).startsWith("2023-0" + finalI)).toList();

            if (collect.size() == 0) {
                insertValue = 0;
            } else {
                for (Sale sale : collect) {
                    int dailySale = sale.getSaleCount() * sale.getSaleItemPrice();
                    insertValue += dailySale;
                }
            }
            resultMap.put("2023-0" + finalI, insertValue);
            insertValue = 0;
        }
        return resultMap;
    }

    public Map<LocalDate, Integer> findAllByWeekDate() {
        HashMap<LocalDate, Integer> resultMap = new HashMap<>();
        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now();
        List<Sale> allBySaleDateBetween = saleRepository.findBySaleDateBetween(startDate, endDate);

        for (int i = 0; i < 8; i++) {
            int minusDate = i;
            int totalCount = 0;
            LocalDate insertDate = null;

            List<Sale> nowData = allBySaleDateBetween.stream()
                    .filter(n -> n.getSaleDate().isEqual(LocalDate.now().minusDays(minusDate))).toList();
            if (nowData.size() == 0) {
                insertDate = LocalDate.now();
                totalCount = 0;
            } else {
                for (Sale sale : nowData) {
                    int oneCount = sale.getSaleCount() * sale.getSaleItemPrice();
                    insertDate = sale.getSaleDate();
                    totalCount += oneCount;

                }
            }
            resultMap.put(insertDate, totalCount);
            insertDate = null;
            totalCount = 0;
        }
        return resultMap;
    }

    public Map<String,Integer> findSaleByItemCode(ItemCode itemCode) {
        HashMap<String, Integer> resultMap = new HashMap<>();
        String initItem;
        int initValue;

        List<Sale> saleList = saleRepository.findBySaleItemCode(itemCode);
        int year = LocalDate.now().getYear();
        int monthValue = LocalDate.now().getMonthValue();

        List<Sale> collect = saleList.stream()
                .filter(s -> s.getSaleDate().getYear() == year && s.getSaleDate().getMonthValue() == monthValue).toList();

        for (Sale sale : collect) {
           initItem = sale.getSaleItemName();
           initValue = sale.getSaleCount() * sale.getSaleItemPrice();

            if (resultMap.containsKey(sale.getSaleItemName())) {
                initValue += resultMap.get(initItem);
            }
            resultMap.put(initItem,initValue);
        }
        return resultMap;
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
    public void saveListSale(List<SaveSaleDto> dto) {
        for (SaveSaleDto saleDto:dto) {
            Sale sale = Sale.builder()
                    .saleItemPrice(saleDto.getItemPrice())
                    .saleCount(saleDto.getItemCount())
                    .saleDate(saleDto.getSaleDate())
                    .saleItemCode(saleDto.getItemCode())
                    .saleItemName(saleDto.getItemName())
                    .build();
            saleRepository.save(sale);
        }
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
