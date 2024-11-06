package com.sum.Security.Request;

import com.sum.Security.user.modal.type.ActivityLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietitianUpdateRequest {
    private Integer userid;
    private String specialization;
    private Integer experienceYear;
    private String certification;
    private Integer age;
}
