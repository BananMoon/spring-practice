package jpabook.jpashop.service;

import jpabook.jpashop.controller.ItemForm;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Repository에게 위임하는 단계
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void save(ItemForm itemForm)  {
        itemRepository.save(itemForm.toEntity());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }
    public Item findItem(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
