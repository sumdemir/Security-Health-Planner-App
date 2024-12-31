package com.sum.Security.Controller;


import com.sum.Security.Request.TrainingPlanRequest;
import com.sum.Security.Service.TrainingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainingplan")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TrainingPlanController {

    private final TrainingPlanService trainingPlanService;

    @PostMapping("/getTrainingPlan")
    public ResponseEntity<String> getTrainingPlan(@RequestParam Integer clientId, @RequestParam Integer trainerId){
        try{

            String trainingPlanResponse = trainingPlanService.getTrainingPlan(clientId, trainerId);
            return ResponseEntity.ok(trainingPlanResponse);

        }catch (Exception e){
            return ResponseEntity.status(500).body("Error occured while fetching the training plan: "
            + e.getMessage());
        }
    }

    @PostMapping("/getTrainingPlanChat")
    public ResponseEntity<String> getTrainingPlanChat(@RequestBody TrainingPlanRequest request) {
        try {
            String trainingPlanResponse = trainingPlanService.getTrainingPlanChat(request.getClientId(), request.getTrainerId());
            return ResponseEntity.ok(trainingPlanResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while fetching the training plan: " + e.getMessage());
        }
    }


}
