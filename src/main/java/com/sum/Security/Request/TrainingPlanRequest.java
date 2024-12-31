package com.sum.Security.Request;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TrainingPlanRequest {
    private Integer clientId;
    private Integer trainerId;
}
