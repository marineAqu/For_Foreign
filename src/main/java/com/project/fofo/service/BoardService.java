package com.project.fofo.service;

import com.project.fofo.DTO.BoardDTO;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        public void updateBookmarkStatus(Long postId, char status) {
            WordsEntity wordsEntity = boardRepository.findById(postId).orElse(null);
            if (wordsEntity != null) {
                wordsEntity.setCheckStatus(status);
                boardRepository.save(wordsEntity);
            }
        }
}
