package com.sum.Security.Controller;

import com.sum.Security.Request.ClientUpdateRequest;
import com.sum.Security.Service.ClientService;
import com.sum.Security.Service.DietPlanService;
import com.sum.Security.auth.AuthenticationResponse;
import com.sum.Security.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    private final ClientService service;
    private final DietPlanService dietPlanService;

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody ClientUpdateRequest request) {
        try {
            service.update(request);
            return ResponseEntity.ok("Profile updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the profile.");
        }
    }

    @PostMapping("/get-diet-plan")
    public void getDietPlan(@RequestParam Integer clientId, @RequestParam Integer dietitianId){
        dietPlanService.getDietPlan(clientId, dietitianId);
    }
}
