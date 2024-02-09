package com.santam.certification_nlw.modules.certifications.controllers;

import com.santam.certification_nlw.modules.certifications.useCases.TopRankingUseCase;
import com.santam.certification_nlw.modules.students.entities.CertificationStudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankController {
    @Autowired
    private TopRankingUseCase topRankingUseCase;

    @GetMapping("top-10")
    public List<CertificationStudentEntity> top10() {
        return topRankingUseCase.execute();
    }

}
