package com.sum.Security.Controller;

import com.sum.Security.DTO.MealCaloriesDTO;
import com.sum.Security.Request.MealCaloriesRequest;
import com.sum.Security.Service.MealCalorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mealCalorie")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MealCaloriesController {

    private final MealCalorieService mealCalorieService;

    @PostMapping ("/saveMealCaloriesDTO")
    public ResponseEntity<MealCaloriesDTO> saveMealCaloriesDTO(@RequestBody MealCaloriesRequest request){
        Integer clientId = request.getClientId();
        MealCaloriesDTO mealCaloriesDTO = request.getMealCaloriesDTO();

        MealCaloriesDTO savedMealCaloriesDTO = mealCalorieService.saveMealCaloriesDTO(mealCaloriesDTO, clientId);

        return ResponseEntity.ok(savedMealCaloriesDTO);

    }

    @GetMapping("/getAllMealsForUser")
    public ResponseEntity<List<MealCaloriesDTO>> getAllMealsForUser(@RequestParam Integer clientId){
        try{
            List<MealCaloriesDTO> mealCalories = mealCalorieService.getAllMealCaloriesForUser(clientId);
            return ResponseEntity.ok(mealCalories);
        } catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }
}
