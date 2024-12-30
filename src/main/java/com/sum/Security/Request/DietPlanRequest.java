package com.sum.Security.Request;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DietPlanRequest {
    private Integer clientId;
    private Integer dietitianId;
}
