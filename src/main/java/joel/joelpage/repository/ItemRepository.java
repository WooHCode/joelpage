package joel.joelpage.repository;

import joel.joelpage.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findOneByName(String name);
    List<Item> findByName(String name);

    List<Item> findListByName(String name);
    Page<Item> findAll(Pageable pageable);

    List<Item> findByNameLike(String likeName);
}
