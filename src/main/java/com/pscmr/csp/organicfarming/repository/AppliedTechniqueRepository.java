package com.pscmr.csp.organicfarming.repository;


import com.pscmr.csp.organicfarming.model.AppliedTechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppliedTechniqueRepository extends JpaRepository<AppliedTechnique, Long> {
    List<AppliedTechnique> findByUserId(Long userId);
    Optional<AppliedTechnique> findByUserIdAndTechniqueId(Long userId, Long techniqueId);
    List<AppliedTechnique> findBySavedOfflineTrueAndUserId(Long userId);
    int countByTechniqueId(Long techniqueId);
}