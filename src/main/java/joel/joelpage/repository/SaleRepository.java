package joel.joelpage.repository;

import joel.joelpage.entity.ItemCode;
import joel.joelpage.entity.Sale;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySaleItemCode(ItemCode itemCode);
    List<Sale> findAllBySaleDateBetween(LocalDate startDate, LocalDate endDate);

    List<Sale> findAllBySaleDateLike(String nowYear);
}
