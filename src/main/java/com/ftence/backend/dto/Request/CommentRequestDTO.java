package com.ftence.backend.dto.Request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.net.InterfaceAddress;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
public class CommentRequestDTO {
    private String intraId;
    private String content;
    private Integer starRating;
    private String timeTaken;
    private String amountStudy;
    private String bonus;
    private String difficulty;
    private String subjectName;
}
