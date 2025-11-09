package com.pscmr.csp.organicfarming.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applied_techniques")
public class AppliedTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "technique_id")
    private FarmingTechnique technique;
    
    private LocalDateTime appliedDate;
    private Integer rating;
    private String feedback;
    private boolean savedOffline = false;
    
    @PrePersist
    protected void onCreate() {
        appliedDate = LocalDateTime.now();
    }
    
    // Constructors, Getters, and Setters
    public AppliedTechnique() {}
    
    public AppliedTechnique(User user, FarmingTechnique technique) {
        this.user = user;
        this.technique = technique;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public FarmingTechnique getTechnique() { return technique; }
    public void setTechnique(FarmingTechnique technique) { this.technique = technique; }
    
    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }
    
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    
    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }
    
    public boolean isSavedOffline() { return savedOffline; }
    public void setSavedOffline(boolean savedOffline) { this.savedOffline = savedOffline; }
}