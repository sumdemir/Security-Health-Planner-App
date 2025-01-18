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
@Table(name = "meal_calories")
public class MealCalories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String calories; // Premium için metin dönebileceğinden String
    private String servingSizeG; // Premium için metin dönebileceğinden String
    private String proteinG; // Premium için metin dönebileceğinden String

    private Double fatTotalG;
    private Double fatSaturatedG;
    private Double carbohydratesTotalG;
    private Double sodiumMg;
    private Double potassiumMg;
    private Double cholesterolMg;
    private Double fiberG;
    private Double sugarG;
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Builder
    public MealCalories(String name, String calories, String servingSizeG, String proteinG,
                        Double fatTotalG, Double fatSaturatedG, Double carbohydratesTotalG,
                        Double sodiumMg, Double potassiumMg, Double cholesterolMg,
                        Double fiberG, Double sugarG, Timestamp createdAt, Client client) {
        this.name = name;
        this.fatTotalG = fatTotalG;
        this.carbohydratesTotalG = carbohydratesTotalG;
        this.sodiumMg = sodiumMg;
        this.potassiumMg = potassiumMg;
        this.cholesterolMg = cholesterolMg;
        this.fiberG = fiberG;
        this.sugarG = sugarG;
        this.createdAt = createdAt;
        this.client = client;
        this.calories = calories;
        this.servingSizeG = servingSizeG;
        this.proteinG = proteinG;
        this.fatSaturatedG = fatSaturatedG;
    }
}
