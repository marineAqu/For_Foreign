package com.project.fofo.service;

import com.project.fofo.DTO.QuizDTO;
import com.project.fofo.DTO.VocaDTO;
import com.project.fofo.DTO.WritingDTO;
import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.entity.WritingboardEntity;
import com.project.fofo.repository.WritingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.Optional;

/**
 * 파일명: WritingService
 * 작성자: 김도연
 **/

@Service
@RequiredArgsConstructor
public class WritingService {
    private final WritingRepository writingRepository;

    public List<WritingDTO> SearchWritingPosts(Date creationDate) {

        List<WritingboardEntity> writingboardEntityList = writingRepository.findByCreationDate(creationDate);

        List<WritingDTO> writingDTOList = new ArrayList<>();
        for(WritingboardEntity writingboardEntity : writingboardEntityList) writingDTOList.add(WritingDTO.toWritingDTO(writingboardEntity));

        System.out.println("WritingService에서: "+ writingDTOList);

        return writingDTOList;
    }

    public void saveWritingTop(String topicContent, Date date) {
        WritingboardEntity writingboardEntity = WritingboardEntity.toPostWriting(topicContent, date);
        System.out.println("saveWritingTop 함수 들어옴 (서비스):"+ writingboardEntity);
        writingRepository.save(writingboardEntity);
    }
}
