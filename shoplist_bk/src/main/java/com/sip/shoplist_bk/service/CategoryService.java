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

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }
    
    public Category findByName(String categoryName) {
		return categoryRepo.findByName(categoryName).orElseThrow();
	}
    
}
