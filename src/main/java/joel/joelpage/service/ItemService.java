package joel.joelpage.service;

import joel.joelpage.entity.Item;
import joel.joelpage.entity.ItemCode;
import joel.joelpage.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * 전체 상품 조회
     * */
    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    /**
     * 상품 이름으로 한건 조회
     */
    public Item findOneItem(Item item) {
        return itemRepository.findById(item.getId()).get();
    }

    /**
     * 상품 한건 저장, 이미 있는 상품인지 중복검사
     */
    @Transactional
    public Long saveItem(Item item){
        validateSavedItem(item);
        itemRepository.save(item);
        return item.getId();
    }

    /**
     * 한 건을 상품 이름으로 가져와서 이미 있다면 Exception
     */
    private void validateSavedItem(Item item) {
        List<Item> findItem = itemRepository.findByName(item.getName());
        if (!findItem.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 상품입니다.");
        }
    }

    /**
     *  상품을 id로 가져와 데이터 수정, 더티체킹으로 업데이트
     */
    @Transactional
    public void updateItem(Long itemId, String itemName, int price, String imgPath, String itemDes , ItemCode itemCode) {
        Item item = itemRepository.findById(itemId).get();
        item.toEntity(itemName,price,imgPath,itemDes,itemCode);
    }
}
