package com.project.fofo.service;

/**
 * 파일명: VocaService
 * 작성자: 김도연
 **/

import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.project.fofo.repository.VocaRepository;

@Service
@RequiredArgsConstructor
public class VocaService {
    private final VocaRepository vocaRepository;
    private final BoardRepository boardRepository;

    public void saveVocaTit(String newVocaTit, Long userNo) {
        VocalistEntity vocalistEntity = VocalistEntity.toVocaEntity(newVocaTit, userNo);
        System.out.println("saveVocaTit 함수 들어옴 (서비스):"+ vocalistEntity);
        vocaRepository.save(vocalistEntity);
    }

    public void saveTransWord(Long vocaNo, String koWord, String enWord) {
        WordsEntity wordsEntity = WordsEntity.toSaveTransWord(vocaNo, koWord, enWord);

        System.out.println("번역탭에서 단어 저장 테스트용 코드 wordsEntity 확인: "+wordsEntity);
        boardRepository.save(wordsEntity);
    }
}
