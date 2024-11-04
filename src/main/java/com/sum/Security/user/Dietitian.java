package com.sum.Security.user;

import com.sum.Security.AIresponse.DietPlan;
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
@Table(name = "dietitian")
@PrimaryKeyJoinColumn(name = "user_id")
public class Dietitian extends User{

    private String specialization;
    private Integer experienceYear;
    private String certification;
    private Integer age;

    @OneToMany(mappedBy = "dietitian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DietPlan> dietPlans;

    @Builder
    public Dietitian(Integer id, String firstname, String lastname, String email, String password,
                     String specialization, Integer experienceYears, String certifications, Integer age) {
        super(id, firstname, lastname, email, password, Role.DIETITIAN);
        this.specialization = specialization;
        this.experienceYear = experienceYears;
        this.certification = certifications;
        this.age = age;
    }

    public void addDietPlan(DietPlan dietPlan) {
        dietPlans.add(dietPlan);
        dietPlan.setDietitian(this);
    }

    public void removeDietPlan(DietPlan dietPlan) {
        dietPlans.remove(dietPlan);
        dietPlan.setDietitian(null);
    }

}
