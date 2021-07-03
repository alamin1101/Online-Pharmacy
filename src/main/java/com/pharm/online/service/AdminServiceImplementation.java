package com.pharm.online.service;

import com.pharm.online.entity.Medicine;
import com.pharm.online.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImplementation implements AdminService{

    @Autowired
    MedicineRepository medicineRepository;


    @Override
    public void addNewMedicine(Medicine medicine) {
        medicineRepository.save(medicine);

    }

    @Override
    public Medicine getMedicineByMedicineId(Integer medicineId) {
        return medicineRepository.findById(medicineId).get();
    }

    @Override
    public void deleteMedicineById(Integer medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    @Override
    public List<Medicine> findMedicineByCatagory(String catagory) {
        return null;
    }

    @Override
    public List<Medicine> findAllMedicine() {
        return medicineRepository.findAll();
    }

    @Override
    public List<Medicine> findAllMedicineByMedicineNameAndCompany(String medicineName, String company) {
        return null;
    }

    @Override
    public List<Medicine> findMedicineByMedicineName(String medicineName) {
        return null;
    }

    @Override
    public List<Medicine> findMedicineByCompany(String company) {
        return null;
    }
}
