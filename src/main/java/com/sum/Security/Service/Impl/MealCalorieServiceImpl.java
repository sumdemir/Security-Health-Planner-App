package com.sum.Security.Service.Impl;

import com.sum.Security.AIresponse.MealCalories;
import com.sum.Security.DTO.MealCaloriesDTO;
import com.sum.Security.Service.MealCalorieService;
import com.sum.Security.repository.ClientRepository;
import com.sum.Security.repository.MealCalorieRepository;
import com.sum.Security.user.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MealCalorieServiceImpl implements MealCalorieService {

    private final MealCalorieRepository mealRepository;
    private final ClientRepository clientRepository;

    @Override
    public MealCaloriesDTO saveMealCaloriesDTO(MealCaloriesDTO mealCaloriesDTO, Integer clientId) {
        // Client'i veritabanından getir
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + clientId));

        // DTO'dan Entity'yi oluştur
        MealCalories mealCalories = MealCalories.builder()
                .name(mealCaloriesDTO.getName())
                .calories(mealCaloriesDTO.getCalories())
                .servingSizeG(mealCaloriesDTO.getServingSizeG())
                .fatSaturatedG(mealCaloriesDTO.getFatSaturatedG())
                .proteinG(mealCaloriesDTO.getProteinG())
                .fatTotalG(mealCaloriesDTO.getFatTotalG())
                .carbohydratesTotalG(mealCaloriesDTO.getCarbohydratesTotalG())
                .sodiumMg(mealCaloriesDTO.getSodiumMg())
                .potassiumMg(mealCaloriesDTO.getPotassiumMg())
                .cholesterolMg(mealCaloriesDTO.getCholesterolMg())
                .fiberG(mealCaloriesDTO.getFiberG())
                .sugarG(mealCaloriesDTO.getSugarG())
                .createdAt(Timestamp.from(Instant.now()))
                .client(client)
                .build();

            mealRepository.save(mealCalories);

        return mealCaloriesDTO;
    }

    @Override
    public List<MealCaloriesDTO> getAllMealCaloriesForUser(Integer clientId) {
        Client client = clientRepository.findById(Math.toIntExact(clientId)).orElseThrow(() -> new RuntimeException("Client not found"));

        List<MealCalories> mealCaloriesList = mealRepository.findByClientId(clientId);
        return mealCaloriesList.stream().map(meal -> new MealCaloriesDTO(
                        meal.getId(),
                        meal.getName(),
                        meal.getFatTotalG(),
                        meal.getCarbohydratesTotalG(),
                        meal.getSodiumMg(),
                        meal.getPotassiumMg(),
                        meal.getCholesterolMg(),
                        meal.getFiberG(),
                        meal.getSugarG(),
                        meal.getFatSaturatedG(),
                        meal.getCreatedAt(),
                        meal.getCalories(),
                        meal.getServingSizeG(),
                        meal.getProteinG()
                )).collect(Collectors.toList());
    }
}
