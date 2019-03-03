package com.example.repository;

import com.example.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("orderDetailRepository")
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}