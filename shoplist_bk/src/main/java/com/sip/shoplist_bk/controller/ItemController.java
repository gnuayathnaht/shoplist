package com.sip.shoplist_bk.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sip.shoplist_bk.entity.Item;
import com.sip.shoplist_bk.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> findAllItems() {
        List<Item> items = itemService.findAllItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Item> saveItem(@RequestBody Item item, @RequestParam Integer categoryId) {
        Item dbItem = itemService.saveItem(item, categoryId);
        return ResponseEntity.ok(dbItem);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Item>> findItemsByCategoryId(@PathVariable Integer id) {
        List<Item> item = itemService.findItemsByCategoryId(id);
        return ResponseEntity.ok(item);
    }
    
    @GetMapping("/itemId/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable Integer id) {
    	
        Item item = itemService.findItemById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/{itemId}/upload")
    public ResponseEntity<String> uploadFile(@PathVariable Integer itemId,
                                             @RequestParam("file") MultipartFile file) throws IOException {
        // Create folder if not exists
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        // Create unique filename
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, file.getBytes());

        // Here you save path in DB (pseudo code)
        Item item = itemService.findItemById(itemId);
        item.setImagePath(fileName);
        itemService.updateItem(item);

        return ResponseEntity.ok(fileName); // send filename back
    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        Path filePath = Paths.get(uploadDir, filename);
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // or detect content type
                .body(resource);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Item>> getSearchItems(@RequestParam("keyword") String keyword){
    	List<Item> items = this.itemService.findItemsByKeyword(keyword);
    	return ResponseEntity.ok(items);
    }
}
