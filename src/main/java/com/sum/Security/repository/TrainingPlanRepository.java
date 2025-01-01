package com.sum.Security.repository;

import com.sum.Security.AIresponse.TrainingPlan;
import com.sum.Security.DTO.TrainingPlanDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Integer> {
    Optional<TrainingPlan> findById(Long id);
}
