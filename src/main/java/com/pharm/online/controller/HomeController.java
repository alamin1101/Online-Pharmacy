package com.pharm.online.controller;

import com.pharm.online.entity.User;
import com.pharm.online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;




    @RequestMapping({"/","","/home"})
    public String home(Model model)
    {
        return "home";
    }
    @GetMapping({"/login"})
    public String login(Model model)
    {
        return "login";
    }
    @PostMapping({"/login"})
    public String login_success(Model model)
    {
        model.addAttribute("message","successfully login.......");
        return "message";
    }

    @RequestMapping("/about")
    public String about()
    {
        return "about";
    }


    @GetMapping({"/signup"})
    public String signup(Model model)
    {
        model.addAttribute("message", null);
        model.addAttribute("user",new User());
        return "regestration-form";
    }

    @PostMapping("/signup-success")
    public String signSuccess(@Valid User user, Model model)
    {

        if(userRepository.countUsersByUsername(user.getUsername())==1) {
            model.addAttribute("message", "username already exist...");
            return "regestration-form";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("message", "password not matched...");
            return "regestration-form";
        }
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);

        if(userRepository.findByRole().isEmpty()) {
            user.setRole("ROLE_ADMIN");
        }
        else {
            user.setRole("ROLE_USER");
        }
        userRepository.save(user);
        model.addAttribute("message","Successfully SignUp");
        return "message";
    }

    
    @RequestMapping("/logout")
    public String logout(@Valid User user)
    {
        return "login";
    }

   
    @RequestMapping("/profile")
    public String profile(Principal principal, Model model)
    {
        User user = userRepository.findById(principal.getName()).get();
        model.addAttribute("user_info", user);
        return "profile";
    }

    @RequestMapping("/profile/settings")
    public String profileSettings(Principal principal, Model model)
    {
        User user = userRepository.findById(principal.getName()).get();
        model.addAttribute("user", user);
        return "profile_settings";
    }

    @RequestMapping("/profile/update")
    public String profileUpdate(@Valid User user,Principal principal,Model model)
    {
        if(!principal.getName().equals(user.getUsername())) {
            // model.addAttribute("name", "username is already exist");
            return "profile_settings";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "profile_settings";
        }
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        userRepository.save(user);
        if(user.getRole().equals("ROLE_ADMIN"))
            return "redirect:/home";
        else
            return "redirect:/home";
    }

}

