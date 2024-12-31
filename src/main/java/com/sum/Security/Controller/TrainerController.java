package com.sum.Security.Controller;

import com.sum.Security.DTO.TrainerDTO;
import com.sum.Security.Request.TrainerUpdateRequest;
import com.sum.Security.Service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {

    private final TrainerService service;

    @PutMapping("/update")
    public void update(
            @RequestBody TrainerUpdateRequest request
    ){
        service.update(request);
    }

    @GetMapping("/getAllTrainers")
    public List<TrainerDTO> getAllTrainers(){
        return service.getAllTrainers();
    }
}
