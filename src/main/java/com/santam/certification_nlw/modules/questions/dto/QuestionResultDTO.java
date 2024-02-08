package com.santam.certification_nlw.modules.questions.dto;

import com.santam.certification_nlw.modules.questions.entities.QuestionEntity;

import java.util.List;
import java.util.UUID;

public record QuestionResultDTO (
         UUID id,
         String technology,
         String description,
         List<AlternativesResultDTO> alternatives
) {
    public QuestionResultDTO(QuestionEntity question) {
        this(question.getId(), question.getTechnology(), question.getDescription(), question.getAlternatives().stream().map(AlternativesResultDTO::new).toList());
    }
}
