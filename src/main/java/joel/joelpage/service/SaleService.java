package joel.joelpage.service;

import joel.joelpage.entity.ItemCode;
import joel.joelpage.entity.Sale;
import joel.joelpage.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Sale> findSaleByItemCode(ItemCode itemCode) {
        return saleRepository.findBySaleItemCode(itemCode);
    }

    @Transactional
    public void saveOneSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Transactional
    public void updateOneSale(Long id, Sale sale) {
        Sale findSale = saleRepository.findById(id).get();
        findSale.toEntity(sale.getSaleItemName(),sale.getSaleItemPrice(),sale.getSaleDate(),sale.getSaleCount(),sale.getItemTotalSale());
    }

    @Transactional
    public void deleteOneSale(Long id) {
        Sale sale = saleRepository.findById(id).get();
        saleRepository.delete(sale);
    }
}
