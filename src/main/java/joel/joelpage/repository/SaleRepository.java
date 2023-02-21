package joel.joelpage.repository;

import joel.joelpage.entity.ItemCode;
import joel.joelpage.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    List<Sale> findBySaleItemCode(ItemCode itemCode);
}
