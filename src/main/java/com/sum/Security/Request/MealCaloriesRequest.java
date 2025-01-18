package com.sum.Security.Request;

import com.sum.Security.DTO.MealCaloriesDTO;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MealCaloriesRequest {
    private Integer clientId;
    private MealCaloriesDTO mealCaloriesDTO;
}
