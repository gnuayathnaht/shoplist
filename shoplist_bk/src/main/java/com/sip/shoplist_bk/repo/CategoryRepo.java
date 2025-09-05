package com.sip.shoplist_bk.repo;

import com.sip.shoplist_bk.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
	Optional<Category> findByName(String name);
}
