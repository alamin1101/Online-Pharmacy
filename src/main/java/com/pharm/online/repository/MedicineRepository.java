package com.pharm.online.repository;

import com.pharm.online.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Integer> {


    @Query("select b from Medicine b where  (?1 is null or b.medicineName like %?1% or b.category like %?1% or b.company like %?1%)")
    List<Medicine> findAllmedicines(String s);


//    @Query("select new Medicine(b.medicineTitle, b.category, count (b)) from medicine b WHERE  (?1 is null or b.medicineTitle like %?1%) GROUP BY b.medicineTitle")
//    List<Medicine> findAllmedicineNumbersByTitle(String title);



}
