package com.sum.Security.Service;

import com.sum.Security.DTO.DietitianDTO;
import com.sum.Security.Request.DietitianUpdateRequest;
import com.sum.Security.user.Dietitian;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DietitianService {
    void update (DietitianUpdateRequest request);

    List<DietitianDTO> getAllDietitians();
}
