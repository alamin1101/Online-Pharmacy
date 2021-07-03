package com.pharm.online.controller;

import com.pharm.online.entity.Medicine;
import com.pharm.online.repository.MedicineRepository;
import com.pharm.online.repository.OrderMedicineRepository;
import com.pharm.online.repository.UserRepository;
import com.pharm.online.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private OrderMedicineRepository orderMedicineRepository;
    

    @RequestMapping("/admin")
    public String admin(Model model, Principal principal)
    {
        model.addAttribute("users",userRepository.count());
          return "admin-home";
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
//    @RequestMapping("/medicine-category")
//    public String adminmedicineCatagory(Model model, @RequestParam(required= false) String s)
//    {
//        model.addAttribute("medicinelist",medicineRepository.findAllmedicineNumbersByCategory(s));
//        return "medicine_category";
//    }
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
//    @RequestMapping("/medicine-category/all")
//    public String adminmedicineCatagory(@RequestParam String category,Model model)
//    {
//        model.addAttribute("medicinelist",medicineRepository.findByCategory(category));
//        return "medicineself";
//    }
//
//
//    @GetMapping("/admin/medicine/update")
//    public String adminmedicineUpdate(@RequestParam int medicineId, Model model){
//        model.addAttribute("medicine", adminService.getmedicinetById(medicineId));
//        return "medicine_update";
//    }
//
//
//    @PostMapping("/admin/medicine/update")
//    public String adminUpdatesuccess(@Valid medicine medicine)
//    {
//
//        adminService.addmedicine(medicine);
//        return "redirect:/medicine-category";
//    }
//
//    @RequestMapping("/admin/medicine/delete")
//    public String adminmedicineDelete(@RequestParam int medicineId){
//        adminService.deletemedicineById(medicineId);
//        return "redirect:/medicine-category";
//    }
//
    @RequestMapping("/medicine-storage")
    public String medicineStorage(Model model,@RequestParam(required = false) String s){
        model.addAttribute("medicinelist", medicineRepository.findAllmedicines(s));
        return "medicine-storage";
    }
//
//    @RequestMapping("admin/userslist")
//    public String adminUserslist(Model model,@RequestParam(required = false) String s) {
//        AppUser appUser=new AppUser();
//        model.addAttribute("userslist", userRepository.findAllUsers(s));
//        return "list_of_users";
//    }
//
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
//    @GetMapping("/admin/medicine/order")
//    public String adminmedicineOrder1()
//    {
//        return "medicine_order";
//    }
//
//
//    @PostMapping("/admin/medicine/order")
//    public String adminmedicineOrder(@ModelAttribute medicineOrder medicineorder) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        if (format.parse(medicineorder.getArivalDate()).before(format.parse(medicineorder.getOrderedDate()))) {
//            return "medicine_order";
//        }
//        medicineOrderRepository.save(medicineorder);
//        return "redirect:/admin/medicine/orderlist";
//    }
//
//    @GetMapping("/admin/medicine/orderlist")
//    public String adminmedicineOrderlist(Model model)
//    {
//        model.addAttribute("orderlist",medicineOrderRepository.findAll());
//        return "medicine_orderlist";
//    }
//
//
//    @RequestMapping("/admin/medicine/order/delete")
//    public String adminmedicineOrderDelete(@RequestParam int orderId)
//    {
//        medicineOrderRepository.deleteById(orderId);
//        return "redirect:/admin/medicine/orderlist";
//
//    }
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
