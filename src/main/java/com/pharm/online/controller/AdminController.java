package com.pharm.online.controller;

import com.pharm.online.entity.Medicine;
import com.pharm.online.entity.MedicineOrder;
import com.pharm.online.entity.User;
import com.pharm.online.repository.MedicineOrderRepository;
import com.pharm.online.repository.MedicineRepository;
import com.pharm.online.repository.UserRepository;
import com.pharm.online.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private MedicineOrderRepository medicineOrderRepository;
    

    @RequestMapping("/admin")
    public String admin(Model model, Principal principal)
    {
        User user=userRepository.findByUsername(principal.getName());
        if(user.getRole().equals("ROLE_ADMIN"))
          return "admin-home";
        else
            return "consumer-home";
    }


    @GetMapping("/add-new-medicine")
    public String adminmedicineAdd(Model model)
    {
        model.addAttribute("medicine", new Medicine());
        return "add-medicine";
    }

    @PostMapping("/add-new-medicine")
    public String adminmedicineAdding(@Valid Medicine medicine)
    {

        adminService.addNewMedicine(medicine);
        return "redirect:/admin";
    }





//
    @RequestMapping("/medicine-category")
    public String adminmedicineCatagory(Model model, @RequestParam(required= false) String s)
    {
        model.addAttribute("medicinelist",medicineRepository.findMedicinesByCategory(s));
        return "medicine-category";
    }
//
//
//    @GetMapping("/admin/medicine/title")
//    public String adminmedicinesTitle(Model model, @RequestParam(required = false) String s) {
//
//        model.addAttribute("medicinelist",medicineRepository.findAllmedicineNumbersByTitle(s));
//        return "medicine_title";
//
//    }
//
//    @RequestMapping("/admin/medicine/title/all")
//    public String adminmedicinesTitle(@RequestParam String medicineTitle,Model model)
//    {
//        model.addAttribute("medicinelist",medicineRepository.findBymedicineTitle(medicineTitle));
//        return "medicineself";
//    }
//
    @RequestMapping("/medicine-category-all")
    public String adminmedicineCatagory(@RequestParam String category,Model model)
    {
        model.addAttribute("medicinelist",adminService.findMedicineByCatagory(category));
        return "medicine-storage";
    }
//
//
    @GetMapping("/update-medicine")
    public String adminmedicineUpdate(@RequestParam int medicineId, Model model){
        model.addAttribute("medicine", medicineRepository.findById(medicineId).get());
        return "update-medicine";
    }


    @PostMapping("/update-medicine")
    public String adminUpdatesuccess(@Valid Medicine medicine)
    {

        adminService.addNewMedicine(medicine);
        return "redirect:/medicine-storage";
    }

    @RequestMapping("/delete-medicine")
    public String adminmedicineDelete(@RequestParam int medicineId){
        adminService.deleteMedicineById(medicineId);
        return "redirect:/medicine-storage";
    }
//
    @RequestMapping("/medicine-storage")
    public String medicineStorage(Model model,@RequestParam(required = false) String s){
        model.addAttribute("medicinelist", medicineRepository.findAllmedicines(s));
        return "medicine-storage";
    }
//
    @RequestMapping("/consumerslist")
    public String adminUserslist(Model model,@RequestParam(required = false) String s) {
        User appUser=new User();
        model.addAttribute("userslist", userRepository.findAllUsers(s));
        return "consumers-list";
    }

//
//    @RequestMapping("/admin/search")
//    public String userSearch(@RequestParam String username, Model model)
//    {
//
//        model.addAttribute("userslist", userRepository.findById(username).get());
//        return "list_of_users";
//    }
//
//
    @GetMapping("/medicine-order")
    public String adminmedicineOrder1()
    {
        return "medicine-order";
    }


    @PostMapping("/medicine-order")
    public String adminmedicineOrder(@ModelAttribute MedicineOrder medicineorder) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (format.parse(medicineorder.getArivalDate()).before(format.parse(medicineorder.getOrderedDate()))) {
            return "medicine-order";
        }
        medicineOrderRepository.save(medicineorder);
        return "redirect:/medicine-order-list";
    }
//
    @GetMapping("/medicine-order-list")
    public String adminmedicineOrderlist(Model model)
    {
        model.addAttribute("orderlist",medicineOrderRepository.findAll());
        return "medicine-order-list";
    }
//
//
    @RequestMapping("/medicine-order-delete")
    public String adminmedicineOrderDelete(@RequestParam int orderId)
    {
        medicineOrderRepository.deleteById(orderId);
        return "redirect:/medicine-order-list";

    }
//
//    @GetMapping("/admin/user/borrow/medicine")
//    public String usermedicineBorrow(@RequestParam int medicineId, Model model){
//        model.addAttribute("medicine", adminService.getmedicinetById(medicineId));
//        return "medicine_borrow_by_user";
//    }
//
//    @PostMapping("/admin/user/borrow/medicine")
//    public String userBorrowmedicine(Model model,@RequestParam int medicineId,@RequestParam String username,@RequestParam String borrowDate,@RequestParam String submitDate) {
//
//        medicine medicine = medicineRepository.findById(medicineId).get();
//        AppUser appUser = userRepository.findById(username).orElse(null);
//        if (appUser.getRole().equals("ROLE_ADMIN"))
//        {
//            return "redirect:/home";
//
//        }
//
//        medicine.setAvailable(false);
//
//        medicineBorrow medicineBorrow = new medicineBorrow();
//        medicineBorrow.setmedicine(medicine);
//        medicineBorrow.setSubmitDate(submitDate);
//        medicineBorrow.setBorrowDate(borrowDate);
//        medicineBorrow.setReturned(false);
//        appUser.getmedicineBorrowList().add(medicineBorrow);
//
//        userRepository.save(appUser);
//
//        return "redirect:/medicine-category";
//    }
//
//
//    @GetMapping("/admin/medicine/borrowerslist")
//    public String fetchBorrowmedicineInfo(Model model, @RequestParam(required = false) String s) {
//        List<AppUser> appUsers = userRepository.findAllByRoleAndSearchValue(s, "ROLE_USER");
//        model.addAttribute("borrowlist",appUsers);
//        return "medicine_borrowers";
//    }
//
//    @GetMapping("/admin/borrow/medicines")
//    public String adminmedicineUserBorrowlist(@RequestParam(name = "user") String username, Model model) {
//        AppUser appUser =  userRepository.findById(username).get();
//        if (appUser == null) {
//            return "redirect:/admin/userslist";
//        }
//        List<medicineBorrow> medicineBorrowList = appUser.getmedicineBorrowList();
//        model.addAttribute("medicineBorrowList", medicineBorrowList);
//        model.addAttribute("username", username);
//        return "borrow_all_medicines";
//    }
//
//    @Transactional
//    @GetMapping("/admin/medicine/return")
//    public String adminmedicineReturn(@RequestParam("medicine") int medicineId, @RequestParam(name = "user") String username, Model model) {
//
//        medicineRepository.updatemedicineAvailability(true, medicineId);
//        medicine medicine = medicineRepository.findById(medicineId).get();
//        borrowRepository.removeByBorrowmedicine(medicine);
//
//        return "redirect:/admin/medicine/borrowerslist";
//    }
//
//
//    @GetMapping("/admin/medicine/borrowers")
//    public String m()
//    {
//        return "redirect:/admin/medicine/borrowerslist";
//    }
//    @GetMapping("/admin/search")
//    public String search(@RequestParam String username,Model model)
//    {
//        model.addAttribute("list",userRepository.findById(username).get());
//        return "/search_page";
//    }




}
