package com.sum.Security.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietPlanRequest {
    private Integer clientId;
    private Integer dietitianId;
}
