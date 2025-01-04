package com.sum.Security.Service;

import com.sum.Security.DTO.DietPlanDTO;

import java.util.List;

public interface DietPlanService {
    String getDietPlan(Integer clientId, Integer dietitianId);
    String getDietPlanChat(Integer clientId, Integer dietitianId);
    DietPlanDTO getDietPlanDTO(Integer clientId, Integer dietitianId);
    List<DietPlanDTO> getAllDietPlansForUser(Integer clientId) throws Exception;
}
