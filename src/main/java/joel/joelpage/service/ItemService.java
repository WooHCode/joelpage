package joel.joelpage.service;

import joel.joelpage.dto.UpdateItemDto;
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
     * 상품 id로 한건 조회
     */
    public Item findOneItem(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    @Transactional
    public UpdateItemDto getUpdateItemDto(Long id) {
        Item item = itemRepository.findById(id).get();
        UpdateItemDto dto = UpdateItemDto.builder()
                .id(item.getId())
                .itemName(item.getName())
                .price(item.getPrice())
                .imgPath(item.getImgPath())
                .itemDes(item.getItemDes())
                .itemCode(item.getItemCode())
                .build();
        return dto;
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

    @Transactional
    public Long saveItemByDto(UpdateItemDto dto) {
        return itemRepository.save(dto.toEntity()).getId();
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
    public Long updateItem(Long itemId, UpdateItemDto updateItemDto) {
        Item item = itemRepository.findById(itemId).get();
        item.toEntity(
                updateItemDto.getItemName(), updateItemDto.getPrice(),
                updateItemDto.getImgPath(), updateItemDto.getItemDes(),updateItemDto.getItemCode());
        return item.getId();
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        itemRepository.delete(item);
    }
}
