package com.pscmr.csp.organicfarming.controller;


import com.pscmr.csp.organicfarming.model.CommunityPost;
import com.pscmr.csp.organicfarming.service.CommunityPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CommunityHubController {
    
    @Autowired
    private CommunityPostService postService;
    
    @GetMapping("/community")
    public String communityHub(Model model) {
        List<CommunityPost> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "community-hub";
    }
    
    @GetMapping("/community/category")
    public String communityByCategory(@RequestParam String category, Model model) {
        List<CommunityPost> posts = postService.findByCategory(category);
        model.addAttribute("posts", posts);
        model.addAttribute("selectedCategory", category);
        return "community-hub";
    }
    
    @GetMapping("/community/search")
    public String searchPosts(@RequestParam String keyword, Model model) {
        List<CommunityPost> posts = postService.searchByKeyword(keyword);
        model.addAttribute("posts", posts);
        model.addAttribute("searchKeyword", keyword);
        return "community-hub";
    }
    
    @GetMapping("/community/post")
    public String viewPost(@RequestParam Long id, Model model) {
        postService.incrementViews(id);
        postService.findById(id).ifPresent(post -> {
            model.addAttribute("post", post);
        });
        return "post-details";
    }
    
    @GetMapping("/community/create")
    public String createPostForm() {
        return "create-post";
    }
}