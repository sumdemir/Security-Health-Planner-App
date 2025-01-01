package com.sum.Security.Controller;

import com.sum.Security.DTO.DietPlanDTO;
import com.sum.Security.Request.DietPlanRequest;
import com.sum.Security.Service.DietPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dietplan")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("/getDietPlanChat")
    public ResponseEntity<String> getDietPlanChat(@RequestBody DietPlanRequest request) {
        try {
            String dietPlanResponse = dietPlanService.getDietPlanChat(request.getClientId(), request.getDietitianId());
            return ResponseEntity.ok(dietPlanResponse);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error occurred while fetching the diet plan: " + e.getMessage());
        }
    }

    @PostMapping("/getDietPlanDTO")
    public ResponseEntity<DietPlanDTO> getDietPlanDTO(@RequestBody DietPlanRequest request){
        try{
            DietPlanDTO dietPlanDTO = dietPlanService.getDietPlanDTO(request.getClientId(),
                    request.getDietitianId());
            return ResponseEntity.ok(dietPlanDTO);
        } catch (Exception e){
            return ResponseEntity.status(500).body(null);
        }
    }


}
