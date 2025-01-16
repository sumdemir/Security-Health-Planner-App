package com.sum.Security.Service.Impl;

import com.sum.Security.DTO.DietitianDTO;
import com.sum.Security.Request.DietitianUpdateRequest;
import com.sum.Security.Service.DietitianService;
import com.sum.Security.repository.DietitianRepository;
import com.sum.Security.user.Dietitian;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DietitianServiceImp implements DietitianService {

    private final DietitianRepository dietitianRepository;

    @Override
    public void update(DietitianUpdateRequest request) {
        Dietitian dietitian =
                dietitianRepository.findById(request.getUserid()).orElseThrow(()->
                        new RuntimeException(
                                "Dietitian not found."
                        ));
        dietitian.setCertification(request.getCertification());
        dietitian.setExperienceYear(request.getExperienceYear());
        dietitian.setAge(request.getAge());
        dietitian.setSpecialization(request.getSpecialization());

        dietitianRepository.save(dietitian);
    }

    @Override
    public List<DietitianDTO> getAllDietitians() {

        return dietitianRepository.findAll().stream().map(
                dietitian -> new DietitianDTO(
                        dietitian.getFirstname(),
                        dietitian.getLastname(),
                        dietitian.getSpecialization(),
                        dietitian.getId(),
                        dietitian.getEmail()
                )).toList();
    }


}
