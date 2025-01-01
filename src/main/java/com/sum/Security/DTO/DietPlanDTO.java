package com.sum.Security.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class DietPlanDTO {
    private Long id;
    private String planName;
    private String planDetails;
    private Timestamp createdAt;
}
