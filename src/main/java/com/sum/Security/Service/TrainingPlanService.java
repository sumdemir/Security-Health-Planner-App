package com.sum.Security.Service;

import com.sum.Security.AIresponse.TrainingPlan;
import com.sum.Security.DTO.TrainingPlanDTO;

import java.util.List;

public interface TrainingPlanService {
    String getTrainingPlan(Integer clientId, Integer trainerId);
    String getTrainingPlanChat (Integer clientId, Integer trainerId);
    List<TrainingPlan> getAllTrainingPlans();
    TrainingPlanDTO getTrainingPlanById(Long id);
    TrainingPlanDTO convertTrainingPlan(Long userId, Long trainerId);
    TrainingPlanDTO getTrainingPlanDTO(Integer clientId, Integer trainerId);
    List<TrainingPlanDTO> getAllTrainingPlansForUser(Integer clientId);
}
