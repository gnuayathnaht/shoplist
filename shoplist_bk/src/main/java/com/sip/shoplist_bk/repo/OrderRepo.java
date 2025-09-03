package com.sip.shoplist_bk.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sip.shoplist_bk.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

}
