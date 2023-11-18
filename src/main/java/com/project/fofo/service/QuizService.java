package com.project.fofo.service;

/**
 * 파일명: QuizService
 * 작성자: 김도연
 **/

import com.project.fofo.DTO.QuizDTO;
import com.project.fofo.DTO.VocaDTO;
import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.repository.QuizRepository;
import com.project.fofo.repository.VocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final VocaRepository vocaRepository;
    private final QuizRepository quizRepository;

    //유저번호에 해당하는 모든 단어장을 반환
    public List<VocaDTO> SearchVocaBook(Long userNm) {

        //아직 로그인쪽이랑 연결 안했으니까 임시로 하드코딩 => 1106 하드코딩 X수정

        List<VocalistEntity> vocalistEntityList = vocaRepository.findByUserNo(userNm);

        List<VocaDTO> reviewDTOList = new ArrayList<>();
        for(VocalistEntity vocalistEntity : vocalistEntityList) reviewDTOList.add(VocaDTO.toVocaDTO(vocalistEntity));

        System.out.println(reviewDTOList);
        return reviewDTOList;
    }

    public QuizDTO SearchByNo(Long no){
        Optional<WordsEntity> byNo = quizRepository.findById(no);
        WordsEntity wordsEntity = byNo.get();

        QuizDTO dto = QuizDTO.toQuizDTO(wordsEntity);
        return dto;
    }

    public List<QuizDTO> findAll() {
        List<WordsEntity> wordsEntityList = quizRepository.findAll();
        List<QuizDTO> quizDTOList = new ArrayList<>();

        for (WordsEntity wordsEntity : wordsEntityList) {
            quizDTOList.add(QuizDTO.toQuizDTO(wordsEntity));
        }
        return quizDTOList;
    }

    public List<QuizDTO> findVocaList(Long vocaNo) {
        /*바로 아래줄에서 partSpeech가 null이면 안 된다는 오류 떠서 일단은 디폴트로 1 혹은 2 값 넣어줌
        오류:
        Can not set char field study.projectkorean.WordsEntity.partSpeech to null value
        */
        List<WordsEntity> wordsEntityList = quizRepository.findByVocaNo(vocaNo);
        List<QuizDTO> quizDTOList = new ArrayList<>();

        for (WordsEntity wordsEntity : wordsEntityList) {
            quizDTOList.add(QuizDTO.toQuizDTO(wordsEntity));
        }
        return quizDTOList;
    }

    public List<Long> findIdList() {
        //모든 id를 찾아 반환하는 메소드 (퀴즈 오답 버튼 만드는 데 사용)
        //1118수정사항: 다른 유저, 다른 단어장의 단어도 포함했는데 사용자가 선택한 해당 단어장에서만 단어를 가져오도록 수정 (사용하지 않음)
        List<WordsEntity> wordsEntityList = quizRepository.findAll();
        List<Long> idList = new ArrayList<>();

        for (int i = 0; i < wordsEntityList.size(); i++) idList.add(wordsEntityList.get(i).getNo());
        //idList[i] = quizEntityList.get(i).getId();

        return idList;
    }
}
