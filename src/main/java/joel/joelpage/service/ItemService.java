package joel.joelpage.service;

import joel.joelpage.dto.UpdateItemDto;
import joel.joelpage.entity.Item;
import joel.joelpage.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     * 전체 상품 페이지로 조회
     */
    public Page<Item> findAllItemPages(Pageable pageable) {
        Page<Item> items = itemRepository.findAll(pageable).map(item -> item);
        return items;
    }

    /**
     * 상품 id로 한건 조회
     */
    public Item findOneItem(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    /**
     * 상품 이름로 한건 조회
     */
    public Item findOneItemByName(String itemName){
       return itemRepository.findOneByName(itemName);
    }

    /**
     * 상품 이름으로 여러 건 조회(Like Query)
     */
    public List<Item> findItemsByNameLike(String likeName) {
        List<Item> nameLike = itemRepository.findByNameLike("%"+likeName+"%");
        if (nameLike.isEmpty()){
            System.out.println("비어있습니다.");
            return null;
        }
        return nameLike;
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
    public String updateItemByName(String name, UpdateItemDto dto) {
        Item item = itemRepository.findOneByName(name);
        item.toEntity(dto.getItemName(), dto.getPrice(), dto.getImgPath(), dto.getItemDes(), dto.getItemCode());
        return item.getName();
    }

    @Transactional
    public void deleteItem(Long itemId) {
        Item item = itemRepository.findById(itemId).get();
        itemRepository.delete(item);
    }
}
