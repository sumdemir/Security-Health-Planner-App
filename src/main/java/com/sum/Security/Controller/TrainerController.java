package com.sum.Security.Controller;

import com.sum.Security.Request.DietitianUpdateRequest;
import com.sum.Security.Request.TrainerUpdateRequest;
import com.sum.Security.Service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/trainer")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService service;

    @PutMapping("/update")
    public void update(
            @RequestBody TrainerUpdateRequest request
    ){
        service.update(request);
    }
}
