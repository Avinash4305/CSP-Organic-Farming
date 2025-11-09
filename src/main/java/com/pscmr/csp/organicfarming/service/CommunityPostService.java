package com.pscmr.csp.organicfarming.service;


import com.pscmr.csp.organicfarming.model.CommunityPost;
import com.pscmr.csp.organicfarming.repository.CommunityPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityPostService {
    
    @Autowired
    private CommunityPostRepository postRepository;
    
    public List<CommunityPost> findAll() {
        return postRepository.findAllOrderByCreatedAtDesc();
    }
    
    public Optional<CommunityPost> findById(Long id) {
        return postRepository.findById(id);
    }
    
    public List<CommunityPost> findByCategory(String category) {
        return postRepository.findByCategoryOrderByCreatedAtDesc(category);
    }
    
    public List<CommunityPost> findByUserId(Long userId) {
        return postRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
    
    public List<CommunityPost> searchByKeyword(String keyword) {
        return postRepository.searchByKeyword(keyword);
    }
    
    public CommunityPost save(CommunityPost post) {
        return postRepository.save(post);
    }
    
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
    
    public void incrementViews(Long postId) {
        postRepository.findById(postId).ifPresent(post -> {
            post.setViews(post.getViews() + 1);
            postRepository.save(post);
        });
    }
    
    public void incrementLikes(Long postId) {
        postRepository.findById(postId).ifPresent(post -> {
            post.setLikes(post.getLikes() + 1);
            postRepository.save(post);
        });
    }
}