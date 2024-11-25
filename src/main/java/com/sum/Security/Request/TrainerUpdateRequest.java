package com.sum.Security.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainerUpdateRequest {
    private Integer userid;
    private String specialization;
    private Integer experienceYear;
    private String certification;
    private Integer age;
}
