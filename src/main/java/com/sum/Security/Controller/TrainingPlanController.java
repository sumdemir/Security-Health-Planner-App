package com.sum.Security.Controller;

import com.sum.Security.Service.TrainingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainingplan")
@RequiredArgsConstructor
public class TrainingPlanController {

    private final TrainingPlanService trainingPlanService;

    @PostMapping("/getDietPlan")
    public ResponseEntity<String> getTrainingPlan(@RequestParam Integer clientId, @RequestParam Integer trainerId){
        try{

            String trainingPlanResponse = trainingPlanService.getTrainingPlan(clientId, trainerId);
            return ResponseEntity.ok(trainingPlanResponse);

        }catch (Exception e){
            return ResponseEntity.status(500).body("Error occured while fetching the training plan: "
            + e.getMessage());
        }
    }
}
