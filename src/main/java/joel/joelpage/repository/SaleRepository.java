package joel.joelpage.repository;

import joel.joelpage.entity.ItemCode;
import joel.joelpage.entity.Sale;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySaleItemCode(ItemCode itemCode);
    List<Sale> findBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT s FROM Sale s WHERE s.saleDate >= :startDate AND s.saleDate < :endDate")
    List<Sale> findAllBySaleDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
