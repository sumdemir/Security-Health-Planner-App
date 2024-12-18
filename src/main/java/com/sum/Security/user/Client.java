package com.sum.Security.user;

import com.sum.Security.user.modal.type.ActivityLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client extends User {

    private Integer age;
    private Double height;
    private Double weight;
    private String medicalConditions;
    private String goal;

    @Enumerated(EnumType.STRING)
    private ActivityLevel activityLevel;
}
