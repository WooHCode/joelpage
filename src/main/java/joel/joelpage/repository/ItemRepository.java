package joel.joelpage.repository;

import joel.joelpage.entity.Item;
import joel.joelpage.entity.ItemCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findOneByName(String name);
    List<Item> findByName(String name);

    Page<Item> findListByItemCode(ItemCode itemCode, Pageable pageable);
    Page<Item> findAll(Pageable pageable);

    List<Item> findByNameLike(String likeName);

    Page<Item> findByNameLike(String likeName, Pageable pageable);
}
