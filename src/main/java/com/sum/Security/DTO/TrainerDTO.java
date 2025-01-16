package com.sum.Security.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrainerDTO {
    private String firstName;
    private String lastName;
    private String specialization;
    private Integer id;
    private String email;
}
