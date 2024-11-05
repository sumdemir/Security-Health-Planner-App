package com.sum.Security.user;

import com.sum.Security.AIresponse.TrainingPlan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainer")
public class Trainer extends User {

    private String specialization;
    private Integer experienceYear;
    private String certification;
    private Integer age;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingPlan> trainingPlans;

    public void addTrainingPlan(TrainingPlan trainingPlan) {
        trainingPlans.add(trainingPlan);
        trainingPlan.setTrainer(this);
    }

    public void removeTrainingPlan(TrainingPlan trainingPlan) {
        trainingPlans.remove(trainingPlan);
        trainingPlan.setTrainer(null);
    }
}
