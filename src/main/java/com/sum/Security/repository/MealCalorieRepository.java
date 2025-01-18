package com.sum.Security.repository;

import com.sum.Security.AIresponse.MealCalories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MealCalorieRepository extends JpaRepository<MealCalories, Integer> {
    List<MealCalories> findByClientId(Integer clientId);
}
