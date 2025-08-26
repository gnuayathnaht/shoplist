package com.sip.shoplist_bk.repo;

import com.sip.shoplist_bk.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

    List<Item> findByCategoryId(int categoryId);
}
