package com.sum.Security.Controller;


import com.sum.Security.AIresponse.TrainingPlan;
import com.sum.Security.DTO.DietPlanDTO;
import com.sum.Security.DTO.TrainingPlanDTO;
import com.sum.Security.Request.TrainingPlanRequest;
import com.sum.Security.Service.TrainingPlanService;
import com.sum.Security.repository.TrainingPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainingplan")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TrainingPlanController {

    private final TrainingPlanService trainingPlanService;
    private final TrainingPlanRepository trainingPlanRepository;

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

    //IPTAL- DTO İLE ÇALIŞMASI GEREK
    @GetMapping("/getAllTrainingPlans")
    public ResponseEntity<List<TrainingPlan>> getAllTrainingPlans() {
        List<TrainingPlan> trainingPlans = trainingPlanService.getAllTrainingPlans();
        return ResponseEntity.ok(trainingPlans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanDTO> getTrainingPlanById(@PathVariable Long id) {
        TrainingPlanDTO trainingPlanDTO = trainingPlanService.getTrainingPlanById(id);
        return ResponseEntity.ok(trainingPlanDTO);
    }

    @PostMapping("/getTrainingPlanDTO")
    public ResponseEntity<TrainingPlanDTO> getTrainingPlanDTO(@RequestBody TrainingPlanRequest request) {
        try {
            TrainingPlanDTO trainingPlanDTO = trainingPlanService.getTrainingPlanDTO(request.getClientId(),
                    request.getTrainerId());
            return ResponseEntity.ok(trainingPlanDTO);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/getAllTrainingPlansForUser")
    public ResponseEntity<List<TrainingPlanDTO>> getAllTrainingPlansForUser(@RequestParam Integer clientId) {
        try{
            List<TrainingPlanDTO> trainingPlans = trainingPlanService.getAllTrainingPlansForUser(clientId);
            return ResponseEntity.ok(trainingPlans);
        } catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }



}










