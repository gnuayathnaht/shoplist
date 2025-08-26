package com.sip.shoplist_bk.service;

import com.sip.shoplist_bk.entity.Category;
import com.sip.shoplist_bk.entity.Item;
import com.sip.shoplist_bk.repo.CategoryRepo;
import com.sip.shoplist_bk.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    public Item update(Item item) {
        return itemRepo.save(item);
    }

    public Item save(Item item, Integer categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if (category.isPresent()) {
            item.setCategory(category.get());
            return itemRepo.save(item);
        }

        throw new RuntimeException("Author not found with id: " + categoryId);
    }

    public List<Item> findAll() {
        return itemRepo.findAll();
    }

    public Item findById(Integer id) {
        return itemRepo.findById(id).get();
    }

    public List<Item> findItemsByCategoryId(Integer id) {
        return itemRepo.findByCategoryId(id);
    }
}
