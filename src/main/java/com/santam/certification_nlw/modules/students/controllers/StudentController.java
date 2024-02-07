package com.santam.certification_nlw.modules.students.controllers;

import com.santam.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.santam.certification_nlw.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @PostMapping("/verify-if-has-certification")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO dto) {
        var result = verifyIfHasCertificationUseCase.execute(dto);
        if (result) {
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova";
    }
}
