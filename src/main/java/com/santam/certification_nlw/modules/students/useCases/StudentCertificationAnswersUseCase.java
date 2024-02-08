package com.santam.certification_nlw.modules.students.useCases;

import com.santam.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.santam.certification_nlw.modules.questions.entities.QuestionEntity;
import com.santam.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.santam.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.santam.certification_nlw.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCertificationAnswersUseCase {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public StudentCertificationAnswerDTO execute(StudentCertificationAnswerDTO studentCertificationAnswer) {
        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(studentCertificationAnswer.technology());

        studentCertificationAnswer
            .questionsAnswers()
            .forEach(questionAnswer  -> {
                var question = questionsEntity.stream().filter(
                        q -> q.getId().equals(questionAnswer.getQuestionID())
                ).findFirst().get();

                var findCorrectAlternative = question.getAlternatives().stream().filter(AlternativesEntity::isCorrect).findFirst().get();

                var alternativeAnswerIsCorrect = findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID());
                questionAnswer.setCorrect(alternativeAnswerIsCorrect);
            });

        return studentCertificationAnswer;
    }
}
