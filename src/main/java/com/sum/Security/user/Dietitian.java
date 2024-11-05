package com.sum.Security.user;

import lombok.*;
import jakarta.persistence.*;
import com.sum.Security.AIresponse.DietPlan;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dietitan")
public class Dietitian extends User {

    private String specialization;
    private Integer experienceYear;
    private String certification;
    private Integer age;

    @OneToMany(mappedBy = "dietitian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DietPlan> dietPlans;


    public void addDietPlan(DietPlan dietPlan) {
        dietPlans.add(dietPlan);
        dietPlan.setDietitian(this);
    }

    public void removeDietPlan(DietPlan dietPlan) {
        dietPlans.remove(dietPlan);
        dietPlan.setDietitian(null);
    }
}
