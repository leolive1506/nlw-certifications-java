package com.santam.certification_nlw.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionAnswerDTO {
    private UUID questionID;
    private UUID alternativeID;
    private boolean isCorrect;
}
