package com.sum.Security.Controller;

import com.sum.Security.DTO.DietitianDTO;
import com.sum.Security.Request.ClientUpdateRequest;
import com.sum.Security.Request.DietitianUpdateRequest;
import com.sum.Security.Service.DietPlanService;
import com.sum.Security.Service.DietitianService;
import com.sum.Security.repository.DietitianRepository;
import com.sum.Security.user.Dietitian;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dietitian")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DietitianController {

    private final DietitianService service;
    private final DietPlanService dietPlanService;

    @PutMapping("/update")
    public void update(
            @RequestBody DietitianUpdateRequest request
    ){
        service.update(request);
    }

    @GetMapping("/getAllDietitians")
    public List<DietitianDTO> getAllDietitians(){
        return service.getAllDietitians();
    }
}







