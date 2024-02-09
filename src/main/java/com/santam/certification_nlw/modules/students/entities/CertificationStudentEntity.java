package com.santam.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.santam.certification_nlw.modules.students.dto.QuestionAnswerDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certifications")
public class CertificationStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "student_id")
    private UUID studentID;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    @JsonManagedReference
    private StudentEntity studentEntity;

    @Column(length = 100)
    private String technology;
    @Column(length = 10)
    private int grate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_certification_id", insertable = false, updatable = false)
    List<AnswersCertificationsEntity> answersCertificationsEntity = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    public CertificationStudentEntity(UUID studentID, String technology) {
        this.studentID = studentID;
        this.technology = technology;
    }

    public void addAnswersCertificationsEntity(QuestionAnswerDTO dto) {
        answersCertificationsEntity.add(new AnswersCertificationsEntity(dto, this));
    }
}
