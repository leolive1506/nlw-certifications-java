package com.santam.certification_nlw.modules.students.useCases;

import com.santam.certification_nlw.modules.questions.entities.AlternativesEntity;
import com.santam.certification_nlw.modules.questions.entities.QuestionEntity;
import com.santam.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.santam.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.santam.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.santam.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.santam.certification_nlw.modules.students.entities.StudentEntity;
import com.santam.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import com.santam.certification_nlw.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentCertificationAnswersUseCase {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    private StudentCertificationAnswerDTO studentCertificationAnswer;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO studentCertificationAnswerDTO) throws Exception {
        this.studentCertificationAnswer = studentCertificationAnswerDTO;
        verifyIfHasCertification();

        var questions = findQuestionsByTechnology();
        var student = findStudentByEmailOrCreate();
        var certificationStudent = createCertificationStudent(student);

        studentCertificationAnswer
            .questionsAnswers()
            .forEach(questionAnswer -> {
                var question = findQuestionByIdFromList(questions, questionAnswer.getQuestionID());
                var alternativeAnswerIsCorrect = verifyIfCurrentAlternativeIsCorrect(question, questionAnswer.getAlternativeID());
                questionAnswer.setCorrect(alternativeAnswerIsCorrect);
                if (alternativeAnswerIsCorrect) {
                    certificationStudent.setGrate(certificationStudent.getGrate() + 1);
                }
                certificationStudent.addAnswersCertificationsEntity(questionAnswer);
            });

        return certificationStudentRepository.save(certificationStudent);
    }

    private void verifyIfHasCertification() throws Exception {
        var hasCertification = verifyIfHasCertificationUseCase.execute(new VerifyHasCertificationDTO(studentCertificationAnswer.email(), studentCertificationAnswer.technology()));
        if (hasCertification) {
            throw new Exception("Você já tirou sua certificação");
        }
    }

    private List<QuestionEntity> findQuestionsByTechnology() {
        return questionRepository.findByTechnology(studentCertificationAnswer.technology());
    }

    private QuestionEntity findQuestionByIdFromList(List<QuestionEntity> questions, UUID id) {
        return questions.stream().filter(q -> q.getId().equals(id))
                .findFirst()
                .get();
    }

    private AlternativesEntity findCorrectAlternativeFromQuestion(QuestionEntity question) {
        return question.getAlternatives().stream().filter(AlternativesEntity::isCorrect).findFirst().get();
    }

    private boolean verifyIfCurrentAlternativeIsCorrect(QuestionEntity question, UUID alternativeID) {
        var correctAlternative = findCorrectAlternativeFromQuestion(question);
        return correctAlternative.getId().equals(alternativeID);
    }

    private StudentEntity findStudentByEmailOrCreate() {
        var student = studentRepository.findByEmail(studentCertificationAnswer.email()).orElse(null);

        if (student == null) {
            student = studentRepository.save(new StudentEntity(studentCertificationAnswer.email()));
        }

        return student;
    }

    private CertificationStudentEntity createCertificationStudent(StudentEntity student) {
        CertificationStudentEntity certificationStudentEntity = new CertificationStudentEntity(student.getId(), studentCertificationAnswer.technology());
        return certificationStudentRepository.save(certificationStudentEntity);
    }
}
