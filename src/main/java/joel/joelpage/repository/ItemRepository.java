package joel.joelpage.repository;

import joel.joelpage.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findOneByName(String name);
    List<Item> findByName(String name);

    List<Item> findListByName(String name);

}
