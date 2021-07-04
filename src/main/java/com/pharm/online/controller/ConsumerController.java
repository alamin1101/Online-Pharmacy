package com.pharm.online.controller;

import com.pharm.online.repository.MedicineOrderRepository;
import com.pharm.online.repository.MedicineRepository;
import com.pharm.online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ConsumerController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineRepository medicineRepository;


    @RequestMapping("/consumer")
    public String admin(Model model, Principal principal)
    {
        model.addAttribute("users",userRepository.count());
        return "consumer-home";
    }


    @RequestMapping("/medicine-storage-all")
    public String medicineStorage(Model model,@RequestParam(required = false) String s){
        model.addAttribute("medicinelist", medicineRepository.findAllmedicines(s));
        return "view-medicine";
    }


}
