package com.project.fofo.service;

import com.project.fofo.DTO.QuizDTO;
import com.project.fofo.DTO.VocaDTO;
import com.project.fofo.DTO.WritingDTO;
import com.project.fofo.entity.BoardlikeEntity;
import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.entity.WritingboardEntity;
import com.project.fofo.repository.BoardlikeRepository;
import com.project.fofo.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.sql.Date;

/**
 * 파일명: WritingService
 * 작성자: 김도연
 **/

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingRepository writingRepository;
    private final BoardlikeRepository boardlikeRepository;

    public List<WritingDTO> SearchWritingPosts(Date creationDate, Integer userNo) {

        List<WritingboardEntity> writingboardEntityList = writingRepository.findByCreationDate(creationDate);

        List<WritingDTO> writingDTOList = new ArrayList<>();
        for(WritingboardEntity writingboardEntity : writingboardEntityList) writingDTOList.add(WritingDTO.toWritingDTO(writingboardEntity, userNo));
        Collections.sort(writingDTOList, Comparator.comparingLong(WritingDTO::getNo).reversed()); //최신순 정렬

        System.out.println("WritingService에서: "+ writingDTOList);

        return writingDTOList;
    }

    public void saveWritingTop(Long userNo, String topicContent, Date date) {
        WritingboardEntity writingboardEntity = WritingboardEntity.toPostWriting(userNo, topicContent, date);
        System.out.println("saveWritingTop 함수 들어옴 (서비스):"+ writingboardEntity);
        writingRepository.save(writingboardEntity);
    }

    @Transactional
    public void saveIlikeIt(Integer userNo, Long boardNo, int torf) {
        System.out.println("saveIlikeIt 함수 들어옴 (서비스):");

        if(torf == 1){
            //좋아요 삭제
            boardlikeRepository.deleteByBoardNoAndUserNo(boardNo, userNo.longValue());

        }
        else{
            //좋아요 저장
            BoardlikeEntity boardlikeEntity = BoardlikeEntity.toBoardlikeEntity(boardNo, userNo.longValue());
            boardlikeRepository.save(boardlikeEntity);
        }
    }
}
