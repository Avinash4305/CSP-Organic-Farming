//package com.pscmr.csp.organicfarming.controller;
//
//import com.pscmr.csp.organicfarming.model.FarmingTechnique;
//import com.pscmr.csp.organicfarming.service.FarmingTechniqueService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import java.util.List;
//
//@Controller
//public class KnowledgeDashboardController {
//    
//    @Autowired
//    private FarmingTechniqueService techniqueService;
//    
//    @GetMapping("/knowledge-dashboard")
//    public String dashboard(Model model, HttpSession session) {
//        // Check if user is logged in
//        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
//        if (loggedIn != null && loggedIn) {
//            model.addAttribute("loggedIn", true);
//            model.addAttribute("userName", session.getAttribute("userName"));
//        } else {
//            model.addAttribute("loggedIn", false);
//        }
//        
//        List<FarmingTechnique> techniques = techniqueService.findAll();
//        List<String> categories = techniqueService.getDistinctCategories();
//        
//        System.out.println("Techniques found: " + techniques.size());
//        System.out.println("Categories found: " + categories.size());
//        
//        model.addAttribute("techniques", techniques);
//        model.addAttribute("categories", categories);
//        
//        return "knowledge-dashboard";
//    }
//    
//    @GetMapping("/knowledge-dashboard/search")
//    public String searchTechniques(@RequestParam String keyword, Model model, HttpSession session) {
//        // Check if user is logged in
//        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
//        if (loggedIn != null && loggedIn) {
//            model.addAttribute("loggedIn", true);
//            model.addAttribute("userName", session.getAttribute("userName"));
//        } else {
//            model.addAttribute("loggedIn", false);
//        }
//        
//        List<FarmingTechnique> techniques = techniqueService.searchByKeyword(keyword);
//        List<String> categories = techniqueService.getDistinctCategories();
//        
//        model.addAttribute("techniques", techniques);
//        model.addAttribute("categories", categories);
//        model.addAttribute("searchKeyword", keyword);
//        
//        return "knowledge-dashboard";
//    }
//    
//    @GetMapping("/knowledge-dashboard/filter")
//    public String filterTechniques(@RequestParam(required = false) String category, 
//                                  @RequestParam(required = false) String region, 
//                                  Model model, HttpSession session) {
//        // Check if user is logged in
//        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
//        if (loggedIn != null && loggedIn) {
//            model.addAttribute("loggedIn", true);
//            model.addAttribute("userName", session.getAttribute("userName"));
//        } else {
//            model.addAttribute("loggedIn", false);
//        }
//        
//        List<FarmingTechnique> techniques;
//        
//        if (category != null && !category.isEmpty() && region != null && !region.isEmpty()) {
//            // Filter by both category and region
//            techniques = techniqueService.findByCategory(category).stream()
//                    .filter(tech -> region.equals(tech.getRegion()))
//                    .toList();
//        } else if (category != null && !category.isEmpty()) {
//            techniques = techniqueService.findByCategory(category);
//        } else if (region != null && !region.isEmpty()) {
//            techniques = techniqueService.findByRegion(region);
//        } else {
//            techniques = techniqueService.findAll();
//        }
//        
//        List<String> categories = techniqueService.getDistinctCategories();
//        
//        model.addAttribute("techniques", techniques);
//        model.addAttribute("categories", categories);
//        model.addAttribute("selectedCategory", category);
//        model.addAttribute("selectedRegion", region);
//        
//        return "knowledge-dashboard";
//    }
//    
//    @GetMapping("/technique/details")
//    public String techniqueDetails(@RequestParam Long id, Model model, HttpSession session) {
//        // Check if user is logged in
//        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
//        if (loggedIn != null && loggedIn) {
//            model.addAttribute("loggedIn", true);
//            model.addAttribute("userName", session.getAttribute("userName"));
//        } else {
//            model.addAttribute("loggedIn", false);
//        }
//        
//        // Fixed: Check if technique exists and add to model
//        FarmingTechnique technique = techniqueService.findById(id);
//        if (technique != null) {
//            model.addAttribute("technique", technique);
//        }
//        return "technique-details";
//    }
//}