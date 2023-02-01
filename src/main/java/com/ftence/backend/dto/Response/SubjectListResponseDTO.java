package com.ftence.backend.dto.Response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SubjectListResponseDTO {
    private String subjectName;
    private Integer circle;
    private Double avgStarRating;
    private Integer totalRatingNum;
}
