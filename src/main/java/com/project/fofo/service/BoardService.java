package com.project.fofo.service;

/**
 * 파일명: BoardService
 * 작성자: 김현지
 **/

import com.project.fofo.DTO.BoardDTO;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Long savePost(WordsEntity boardDto) {
        return boardRepository.save(boardDto.toEntity()).getNo();
    }


    @Transactional
    public List<WordsEntity> getBoardList() {

        List<BoardDTO> boardDtoList = new ArrayList<>();

        return boardRepository.findAll();
    }

    @Transactional
    public WordsEntity getPost(Long no) {

        return boardRepository.findById(no).get();
    }


    @Transactional
    public void updateBookmarkStatus(Long no, char status) {
        WordsEntity wordsEntity = boardRepository.findById(no).orElse(null);
        if (wordsEntity != null) {
            wordsEntity.setCheckStatus(status);
            boardRepository.save(wordsEntity);
        }
    }

    public void updateWord(Long wordNo, String korWord, String pronSymbol, String engWord, String koSentence, String enSentence) {
        Optional<WordsEntity> optionalWord = boardRepository.findById(wordNo);

        if (optionalWord.isPresent()) {
            WordsEntity word = optionalWord.get();
            word.setKoWord(korWord);
            word.setPronSymbol(pronSymbol);
            word.setEnWord(engWord);
            word.setKoSentence(koSentence);
            word.setEnSentence(enSentence);

            // 다른 필요한 로직 수행...

            boardRepository.save(word); // 수정된 내용으로 저장
        } else {
            // 해당 wordNo에 대한 단어가 존재하지 않을 경우 예외 처리
            throw new EntityNotFoundException("Word with id " + wordNo + " not found");
        }
    }

}
