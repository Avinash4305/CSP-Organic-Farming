package com.pscmr.csp.organicfarming.controller;



import com.pscmr.csp.organicfarming.model.FarmingTechnique;
import com.pscmr.csp.organicfarming.service.FarmingTechniqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class FarmingTechniquesController {
    
    @Autowired
    private FarmingTechniqueService techniqueService;
    
    @GetMapping("/techniques")
    public String techniquesIndex(Model model) {
        List<FarmingTechnique> techniques = techniqueService.findAll();
        List<String> categories = techniqueService.getDistinctCategories();
        
        model.addAttribute("techniques", techniques);
        model.addAttribute("categories", categories);
        
        return "farming-techniques";
    }
    
    @GetMapping("/techniques/category")
    public String techniquesByCategory(@RequestParam String category, Model model) {
        List<FarmingTechnique> techniques = techniqueService.findByCategory(category);
        List<String> categories = techniqueService.getDistinctCategories();
        
        model.addAttribute("techniques", techniques);
        model.addAttribute("categories", categories);
        model.addAttribute("selectedCategory", category);
        
        return "farming-techniques";
    }
    
    @GetMapping("/techniques/search")
    public String searchTechniques(@RequestParam String keyword, Model model) {
        List<FarmingTechnique> techniques = techniqueService.searchByKeyword(keyword);
        List<String> categories = techniqueService.getDistinctCategories();
        
        model.addAttribute("techniques", techniques);
        model.addAttribute("categories", categories);
        model.addAttribute("searchKeyword", keyword);
        
        return "farming-techniques";
    }
}