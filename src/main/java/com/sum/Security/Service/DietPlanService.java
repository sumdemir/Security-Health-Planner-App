package com.sum.Security.Service;

public interface DietPlanService {
    String getDietPlan(Integer clientId, Integer dietitianId);
    String getDietPlanChat(Integer clientId, Integer dietitianId);

}
