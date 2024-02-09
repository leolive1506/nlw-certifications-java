package com.santam.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.santam.certification_nlw.modules.students.dto.QuestionAnswerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certification_students")
public class AnswersCertificationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "certification_id")
    private UUID certificationID;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    private CertificationStudentEntity certificationStudentEntity;

    @Column(name = "student_id")
    private UUID studentID;
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    @Column(name = "question_id")
    private UUID questionID;
    @Column(name = "answer_id")
    private UUID answerID;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public AnswersCertificationsEntity(QuestionAnswerDTO dto, CertificationStudentEntity certificationStudent) {
        this.questionID = dto.getQuestionID();
        this.answerID = dto.getAlternativeID();
        this.isCorrect = dto.isCorrect();
        this.certificationID = certificationStudent.getId();
        this.studentID = certificationStudent.getStudentID();
    }
}
