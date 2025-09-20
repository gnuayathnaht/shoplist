package com.sip.shoplist_bk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.shoplist_bk.entity.Category;
import com.sip.shoplist_bk.repo.CategoryRepo;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }
    
    public Category findCategoryByName(String categoryName) {
		return categoryRepo.findByName(categoryName).orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));
	}
    
    
}
