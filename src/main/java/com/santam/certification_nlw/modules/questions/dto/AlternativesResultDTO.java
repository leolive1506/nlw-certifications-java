package com.santam.certification_nlw.modules.questions.dto;

import com.santam.certification_nlw.modules.questions.entities.AlternativesEntity;

import java.util.UUID;

public record AlternativesResultDTO(
        UUID id,
        String description
) {
    public AlternativesResultDTO(AlternativesEntity alternative) {
        this(alternative.getId(), alternative.getDescription());
    }
}
