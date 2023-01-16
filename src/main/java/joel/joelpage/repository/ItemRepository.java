package joel.joelpage.repository;

import joel.joelpage.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findOneByName(String name);
    List<Item> findByName(String name);
}
