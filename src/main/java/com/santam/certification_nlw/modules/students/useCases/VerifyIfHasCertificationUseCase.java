package com.santam.certification_nlw.modules.students.useCases;

import com.santam.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import com.santam.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(VerifyHasCertificationDTO dto) {
        var result = certificationStudentRepository.findByStudentEmailAndTechnology(dto.email(), dto.technology());
        return !result.isEmpty();
    }
}
