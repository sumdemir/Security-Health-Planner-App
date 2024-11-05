package com.sum.Security.AIresponse;

import com.sum.Security.user.Client;
import com.sum.Security.user.Trainer;
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
@Table(name = "training_plan")
public class TrainingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planName;

    @Lob
    private String planDetails;

    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Builder
    public TrainingPlan(String planName,Trainer trainer, String planDetails,
                        Timestamp createdAt) {
        this.planName = planName;
        this.trainer = trainer;
        this.planDetails = planDetails;
        this.createdAt = createdAt;
    }
}
