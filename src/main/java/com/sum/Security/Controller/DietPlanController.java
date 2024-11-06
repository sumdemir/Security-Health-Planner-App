package com.sum.Security.Controller;

import com.sum.Security.Service.DietPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dietplan")
@RequiredArgsConstructor
public class DietPlanController {

    private final DietPlanService dietPlanService;

    @PostMapping("/getDietPlan")
    public ResponseEntity<String> getDietPlan(@RequestParam Integer clientId, @RequestParam Integer dietitianId) {
        try {
            String dietPlanResponse = dietPlanService.getDietPlan(clientId, dietitianId);
            return ResponseEntity.ok(dietPlanResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while fetching the diet plan: " + e.getMessage());
        }
    }
}
