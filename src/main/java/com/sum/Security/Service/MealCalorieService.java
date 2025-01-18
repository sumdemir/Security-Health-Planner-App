package com.sum.Security.Service;

import com.sum.Security.DTO.MealCaloriesDTO;
import java.util.List;

public interface MealCalorieService {
    MealCaloriesDTO saveMealCaloriesDTO(MealCaloriesDTO mealCaloriesDTO, Integer clientId);
    List<MealCaloriesDTO> getAllMealCaloriesForUser(Integer clientId);
}
