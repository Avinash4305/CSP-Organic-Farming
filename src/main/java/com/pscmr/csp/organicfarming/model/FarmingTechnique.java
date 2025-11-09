package com.pscmr.csp.organicfarming.model;



import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farming_techniques")
public class FarmingTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String steps;
    
    private String category;
    private String difficulty;
    private String season;
    private String region;
    private String imageUrl;
    private double rating = 0.0;
    private int ratingCount = 0;
    private boolean availableOffline = false;
    
    @OneToMany(mappedBy = "technique", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AppliedTechnique> appliedByUsers = new ArrayList<>();
    
    // Constructors, Getters, and Setters
    public FarmingTechnique() {}
    
    public FarmingTechnique(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getSteps() { return steps; }
    public void setSteps(String steps) { this.steps = steps; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    
    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }
    
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    
    public int getRatingCount() { return ratingCount; }
    public void setRatingCount(int ratingCount) { this.ratingCount = ratingCount; }
    
    public boolean isAvailableOffline() { return availableOffline; }
    public void setAvailableOffline(boolean availableOffline) { this.availableOffline = availableOffline; }
    
    public List<AppliedTechnique> getAppliedByUsers() { return appliedByUsers; }
    public void setAppliedByUsers(List<AppliedTechnique> appliedByUsers) { this.appliedByUsers = appliedByUsers; }
}