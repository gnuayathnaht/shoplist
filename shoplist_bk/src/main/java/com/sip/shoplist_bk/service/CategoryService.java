package com.sip.shoplist_bk.service;

import com.sip.shoplist_bk.entity.Category;
import com.sip.shoplist_bk.entity.Item;
import com.sip.shoplist_bk.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }
}
