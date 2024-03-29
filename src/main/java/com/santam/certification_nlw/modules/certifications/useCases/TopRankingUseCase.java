package com.santam.certification_nlw.modules.certifications.useCases;

import com.santam.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.santam.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopRankingUseCase {
    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationStudentEntity> execute() {
        return certificationStudentRepository.findTop10ByOrderByGrateDesc() ;
    }
}
