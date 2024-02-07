package com.santam.certification_nlw.modules.students.useCases;

import com.santam.certification_nlw.modules.students.dto.VerifyHasCertificationDTO;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {
    public boolean execute(VerifyHasCertificationDTO dto) {
        return dto.email().equals("leoandolivelopes2@gmail.com") && dto.technology().equals("JAVA");
        // if (dto.email().equals("leoandolivelopes2@gmail.com") && dto.technology().equals("JAVA")) {

        // }
    }
}
