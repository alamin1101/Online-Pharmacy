package com.pharm.online.repository;

import com.pharm.online.entity.OrderMedicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderMedicineRepository extends JpaRepository<OrderMedicine,Integer> {
}
