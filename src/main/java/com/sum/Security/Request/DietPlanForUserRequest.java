package com.sum.Security.Request;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DietPlanForUserRequest {
    private Integer clientId;
}
