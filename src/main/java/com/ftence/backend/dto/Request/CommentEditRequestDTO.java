package com.ftence.backend.dto.Request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CommentEditRequestDTO {
    private String intraId;
    private Long commentId;
    private String content;
}
