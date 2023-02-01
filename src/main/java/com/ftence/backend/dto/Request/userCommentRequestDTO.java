package com.ftence.backend.dto.Request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class userCommentRequestDTO {
    private int circle;
    private String subjectName;
    private String content;
    private LocalDateTime updated;
}
