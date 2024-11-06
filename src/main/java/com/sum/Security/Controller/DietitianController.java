package com.sum.Security.Controller;

import com.sum.Security.Request.ClientUpdateRequest;
import com.sum.Security.Request.DietitianUpdateRequest;
import com.sum.Security.Service.DietPlanService;
import com.sum.Security.Service.DietitianService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dietitian")
@RequiredArgsConstructor
public class DietitianController {

    private final DietitianService service;
    private final DietPlanService dietPlanService;

    @PutMapping("/update")
    public void update(
            @RequestBody DietitianUpdateRequest request
    ){
        service.update(request);
    }
}
