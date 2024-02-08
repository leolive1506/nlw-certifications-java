package com.santam.certification_nlw.modules.students.dto;

import java.util.List;

public record StudentCertificationAnswerDTO(
        String email,
        String technology,
        List<QuestionAnswerDTO> questionsAnswers
) {
}
