package com.sip.shoplist_bk.controller;

import com.sip.shoplist_bk.entity.Category;
import com.sip.shoplist_bk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("http://localhost:4200/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAllCategory() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        Category dbCategory = categoryService.save(category);
        return ResponseEntity.ok(dbCategory);
    }
}
