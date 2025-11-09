package com.pscmr.csp.organicfarming.repository;


import com.pscmr.csp.organicfarming.model.FarmingTechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FarmingTechniqueRepository extends JpaRepository<FarmingTechnique, Long> {
    List<FarmingTechnique> findByCategory(String category);
    List<FarmingTechnique> findByRegion(String region);
    List<FarmingTechnique> findByCategoryAndRegion(String category, String region);
    
    @Query("SELECT DISTINCT ft.category FROM FarmingTechnique ft")
    List<String> findDistinctCategories();
    
    @Query("SELECT ft FROM FarmingTechnique ft WHERE ft.title LIKE %:keyword% OR ft.description LIKE %:keyword%")
    List<FarmingTechnique> searchByKeyword(@Param("keyword") String keyword);
    
    List<FarmingTechnique> findByAvailableOfflineTrue();
}