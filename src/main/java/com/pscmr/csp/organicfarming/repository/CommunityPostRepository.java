package com.pscmr.csp.organicfarming.repository;


import com.pscmr.csp.organicfarming.model.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    List<CommunityPost> findByCategoryOrderByCreatedAtDesc(String category);
    List<CommunityPost> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    @Query("SELECT cp FROM CommunityPost cp ORDER BY cp.createdAt DESC")
    List<CommunityPost> findAllOrderByCreatedAtDesc();
    
    @Query("SELECT cp FROM CommunityPost cp WHERE cp.title LIKE %:keyword% OR cp.content LIKE %:keyword%")
    List<CommunityPost> searchByKeyword(@Param("keyword") String keyword);
    
    @Query("SELECT cp FROM CommunityPost cp WHERE cp.category = :category ORDER BY cp.likes DESC")
    List<CommunityPost> findPopularByCategory(@Param("category") String category);
}