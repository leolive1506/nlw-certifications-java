package com.santam.certification_nlw.modules.students.controllers;

import com.santam.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.santam.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.santam.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.santam.certification_nlw.modules.students.useCases.StudentCertificationAnswersUseCase;
import com.santam.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    private StudentCertificationAnswersUseCase studentCertificationAnswersUseCase;

    @PostMapping("/verify-if-has-certification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO dto) {
        var result = verifyIfHasCertificationUseCase.execute(dto);
        if (result) {
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody StudentCertificationAnswerDTO StudentCertificationAnswer) throws Exception {
        try {
            var result = studentCertificationAnswersUseCase.execute(StudentCertificationAnswer);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
