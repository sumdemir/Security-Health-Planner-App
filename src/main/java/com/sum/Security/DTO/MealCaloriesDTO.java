package com.sum.Security.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class MealCaloriesDTO {
    private Long id;
    private String name;
    private Double fatTotalG;
    private Double carbohydratesTotalG;
    private Double sodiumMg;
    private Double potassiumMg;
    private Double cholesterolMg;
    private Double fiberG;
    private Double sugarG;
    private Timestamp createdAt;
    private String calories;
    private String servingSizeG;
    private String proteinG;
}
