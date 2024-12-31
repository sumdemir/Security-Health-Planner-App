package com.sum.Security.Service;

public interface TrainingPlanService {
    String getTrainingPlan(Integer clientId, Integer trainerId);
    String getTrainingPlanChat (Integer clientId, Integer trainerId);
}
