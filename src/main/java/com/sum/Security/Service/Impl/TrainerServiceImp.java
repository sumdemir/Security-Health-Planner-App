package com.sum.Security.Service.Impl;

import com.sum.Security.DTO.TrainerDTO;
import com.sum.Security.Request.TrainerUpdateRequest;
import com.sum.Security.Service.TrainerService;
import com.sum.Security.repository.TrainerRepository;
import com.sum.Security.user.Trainer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerServiceImp implements TrainerService {

    private final TrainerRepository trainerRepository;

    @Override
    public void update(TrainerUpdateRequest request) {
        Trainer trainer =
                trainerRepository.findById(request.getUserid())
                        .orElseThrow(() ->
                                new RuntimeException("Trainer not found."));

        trainer.setCertification(request.getCertification());
        trainer.setExperienceYear(request.getExperienceYear());
        trainer.setAge(request.getAge());
        trainer.setSpecialization(request.getSpecialization());

        trainerRepository.save(trainer);

    }

    @Override
    public List<TrainerDTO> getAllTrainers() {
        return trainerRepository.findAll().stream().map(
                trainer -> new TrainerDTO(
                        trainer.getFirstname(),
                        trainer.getLastname(),
                        trainer.getSpecialization(),
                        trainer.getId()
                )).toList();

    }
}
