package com.sum.Security.user;

import com.sum.Security.AIresponse.TrainingPlan;
import com.sum.Security.user.modal.type.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainer")
@PrimaryKeyJoinColumn(name = "user_id")
public class Trainer extends User{

    private String specialization;
    private Integer experienceYear;
    private String certification;
    private Integer age;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingPlan> trainingPlans;

    @Builder
    public Trainer(Integer id, String firstname, String lastname, String email, String password,
                     String specialization, Integer experienceYears, String certifications, Integer age) {
        super(id, firstname, lastname, email, password, Role.TRAINER);
        this.specialization = specialization;
        this.experienceYear = experienceYears;
        this.certification = certifications;
        this.age = age;
    }

    public void addTrainingPlan (TrainingPlan trainingPlan){
        trainingPlans.add(trainingPlan);
        trainingPlan.setTrainer(this);
    }

    public void removeTrainingPlan (TrainingPlan trainingPlan){
        trainingPlans.add(trainingPlan);
        trainingPlan.setTrainer(this);
    }


}
