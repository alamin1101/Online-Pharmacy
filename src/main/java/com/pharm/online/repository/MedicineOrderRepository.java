package com.pharm.online.repository;

import com.pharm.online.entity.MedicineOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineOrderRepository extends JpaRepository<MedicineOrder,Integer> {
}
