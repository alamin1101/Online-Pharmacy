package com.pharm.online.service;

import com.pharm.online.entity.Medicine;

import java.util.List;

public interface AdminService {

    void addNewMedicine(Medicine medicine);
    Medicine getMedicineByMedicineId(Integer medicineId);
    void deleteMedicineById(Integer medicineId);
    List<Medicine> findMedicineByCatagory(String catagory);
    List<Medicine> findAllMedicine();
    List<Medicine>findAllMedicineByMedicineNameAndCompany(String medicineName,String company);
    List<Medicine>findMedicineByMedicineName(String medicineName);
    List<Medicine>findMedicineByCompany(String company);

}
