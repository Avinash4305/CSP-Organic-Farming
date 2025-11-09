package com.pscmr.csp.organicfarming.controller;

import com.pscmr.csp.organicfarming.model.User;
import com.pscmr.csp.organicfarming.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        // If already logged in, redirect to home
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, 
                           @RequestParam String password,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        try {
            // Check if user exists with this email
            var userOpt = userService.findByEmail(email);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                // Validate password (plain text comparison for now)
                if (user.getPassword().equals(password)) {
                    // Set session attributes
                    session.setAttribute("user", user);
                    session.setAttribute("userId", user.getId());
                    session.setAttribute("userName", user.getFirstName() + " " + user.getLastName());
                    session.setAttribute("userEmail", user.getEmail());
                    session.setAttribute("loggedIn", true);
                    
                    redirectAttributes.addFlashAttribute("success", "Login successful! Welcome back.");
                    return "redirect:/";
                }
            }
            
            redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/login";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Login failed: " + e.getMessage());
            return "redirect:/login";
        }
    }
    
    @GetMapping("/register")
    public String showRegisterForm(HttpSession session) {
        // If already logged in, redirect to home
        if (session.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String email,
                              @RequestParam String username,
                              @RequestParam String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        try {
            // Check if username or email already exists
            if (userService.existsByUsername(username)) {
                redirectAttributes.addFlashAttribute("error", "Username already exists");
                return "redirect:/register";
            }
            
            if (userService.existsByEmail(email)) {
                redirectAttributes.addFlashAttribute("error", "Email already exists");
                return "redirect:/register";
            }
            
            // Create and save user (without password encoding for now)
            User user = new User(username, email, password, firstName, lastName);
            User savedUser = userService.save(user);
            
            // Auto-login after registration
            session.setAttribute("user", savedUser);
            session.setAttribute("userId", savedUser.getId());
            session.setAttribute("userName", savedUser.getFirstName() + " " + savedUser.getLastName());
            session.setAttribute("userEmail", savedUser.getEmail());
            session.setAttribute("loggedIn", true);
            
            redirectAttributes.addFlashAttribute("success", "Registration successful! Welcome to FarmConnect.");
            return "redirect:/";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed: " + e.getMessage());
            return "redirect:/register";
        }
    }
    
    @GetMapping("/logout")
    public String logoutUser(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "You have been logged out successfully.");
        return "redirect:/";
    }
}