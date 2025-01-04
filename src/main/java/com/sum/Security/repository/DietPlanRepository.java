package com.sum.Security.repository;

import com.sum.Security.AIresponse.DietPlan;
import com.sum.Security.user.Client;
import com.sum.Security.user.Dietitian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface DietPlanRepository extends JpaRepository<DietPlan, Integer> {
    List<DietPlan> findByClientId(Integer clientId);
}
