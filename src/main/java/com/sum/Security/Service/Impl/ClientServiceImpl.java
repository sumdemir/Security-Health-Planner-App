package com.sum.Security.Service.Impl;

import com.sum.Security.Request.ClientUpdateRequest;
import com.sum.Security.Service.ClientService;
import com.sum.Security.repository.ClientRepository;
import com.sum.Security.user.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public void update(ClientUpdateRequest request) {
        Client client =
                repository.findById(request.getUserid()).orElseThrow(()->
                        new RuntimeException(
                        "Client not found."
                ));
        client.setActivityLevel(request.getActivityLevel());
        client.setGoal(request.getGoal());
        client.setWeight(request.getWeight());
        client.setAge(request.getAge());
        client.setHeight(request.getHeight());
        client.setMedicalConditions(request.getMedicalConditions());

        repository.save(client);
    }
}
