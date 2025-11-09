package com.pscmr.csp.organicfarming.service;

import com.pscmr.csp.organicfarming.model.FarmingTechnique;
import com.pscmr.csp.organicfarming.repository.FarmingTechniqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FarmingTechniqueService {
    
    @Autowired
    private FarmingTechniqueRepository techniqueRepository;
    
    public List<FarmingTechnique> findAll() {
        try {
            List<FarmingTechnique> techniques = techniqueRepository.findAll();
            System.out.println("Found " + techniques.size() + " techniques in database");
            return techniques;
        } catch (Exception e) {
            System.out.println("Error fetching techniques: " + e.getMessage());
            return List.of();
        }
    }
    
    // Fixed: Return FarmingTechnique directly, not Optional
    public FarmingTechnique findById(Long id) {
        try {
            return techniqueRepository.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Error finding technique by ID: " + e.getMessage());
            return null;
        }
    }
    
    public List<FarmingTechnique> findByCategory(String category) {
        return techniqueRepository.findByCategory(category);
    }
    
    public List<FarmingTechnique> findByRegion(String region) {
        return techniqueRepository.findByRegion(region);
    }
    
    public List<FarmingTechnique> searchByKeyword(String keyword) {
        return techniqueRepository.searchByKeyword(keyword);
    }
    
    public List<String> getDistinctCategories() {
        return techniqueRepository.findDistinctCategories();
    }
    
    public FarmingTechnique save(FarmingTechnique technique) {
        return techniqueRepository.save(technique);
    }
    
    public void deleteById(Long id) {
        techniqueRepository.deleteById(id);
    }
    
    public List<FarmingTechnique> getAvailableOffline() {
        return techniqueRepository.findByAvailableOfflineTrue();
    }
}