package com.sum.Security.user;

import com.sum.Security.user.modal.type.ActivityLevel;
import com.sum.Security.user.modal.type.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {

    private Integer age;
    private Double height;
    private Double weight;
    private String medicalConditions;
    private String goal;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;

    @Builder
    public Client(Integer id, String firstname, String lastname, String email, String password,
                  Double height, Double weight, String medicalConditions, ActivityLevel activityLevel, String goal
    ,Integer age) {
        super(id, firstname, lastname, email, password, Role.CLIENT);
        this.height = height;
        this.weight = weight;
        this.medicalConditions = medicalConditions;
        this.activityLevel = activityLevel;
        this.goal = goal;
        this.age = age;
    }


}
