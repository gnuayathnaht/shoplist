package com.sip.shoplist_bk.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sip.shoplist_bk.entity.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

	List<Item> findByCategoryId(int categoryId);

	@Query("SELECT i FROM Item i WHERE i.name LIKE %:keyword%")
	List<Item> findItemsByKeyword(String keyword);

}