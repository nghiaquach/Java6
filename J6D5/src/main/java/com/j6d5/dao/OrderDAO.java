package com.j6d5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.j6d5.entity.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{
}
