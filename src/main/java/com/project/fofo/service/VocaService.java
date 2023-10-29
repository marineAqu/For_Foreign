package com.project.fofo.service;

/**
 * 파일명: VocaService
 * 작성자: 김도연
 **/

import com.project.fofo.entity.VocalistEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.fofo.repository.VocaRepository;

@Service
@RequiredArgsConstructor
public class VocaService {
    private final VocaRepository vocaRepository;

    public void saveVocaTit(String newVocaTit) {
        VocalistEntity vocalistEntity = VocalistEntity.toVocaEntity(newVocaTit);
        System.out.println("saveVocaTit 함수 들어옴 (서비스):"+ vocalistEntity);
        vocaRepository.save(vocalistEntity);
    }
}
