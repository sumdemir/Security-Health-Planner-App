package com.sum.Security.Service;

import com.sum.Security.DTO.DietPlanDTO;

public interface DietPlanService {
    String getDietPlan(Integer clientId, Integer dietitianId);
    String getDietPlanChat(Integer clientId, Integer dietitianId);
    DietPlanDTO getDietPlanDTO(Integer clientId, Integer dietitianId);
}
