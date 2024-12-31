package com.sum.Security.Service;

import com.sum.Security.DTO.TrainerDTO;
import com.sum.Security.Request.TrainerUpdateRequest;

import java.util.List;

public interface TrainerService {
    void update (TrainerUpdateRequest request);
    List<TrainerDTO> getAllTrainers();
}
