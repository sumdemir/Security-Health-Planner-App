package com.sum.Security.Request;

import com.sum.Security.user.modal.type.ActivityLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateRequest {

    private Integer userid;
    private Integer age;
    private Double height;
    private Double weight;
    private String medicalConditions;
    private String goal;
    private ActivityLevel activityLevel;
}
