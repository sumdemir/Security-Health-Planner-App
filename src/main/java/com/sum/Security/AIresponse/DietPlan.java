package com.sum.Security.AIresponse;

import com.sum.Security.user.Client;
import com.sum.Security.user.Dietitian;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diet_plan")
public class DietPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;

    @Lob
    private String planDetails;

    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "dietitian_id", nullable = false)
    private Dietitian dietitian;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Builder
    public DietPlan(String planName, String planDetails, Dietitian dietitian
    ,Timestamp createdAt) {
        this.planDetails = planDetails;
        this.planName = planName;
        this.dietitian = dietitian;
        this.createdAt = createdAt;

    }
}
