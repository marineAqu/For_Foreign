package com.project.fofo.DTO;


import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WritingboardEntity;
import jakarta.persistence.Column;
import lombok.*;

import java.sql.Date;

/**
 * 파일명: WritingDTO
 * 작성자: 김도연
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WritingDTO {
    private Long no; //번호
    private Long userNo; //유저 번호
    private String content; //게시글 내용
    private Date creationDate; //작성일
    private String userName; //user에서 userName을 받아옴
    private MemlistEntity user; //member테이블에서 조인


    //게시글을 검색하는 DTO
    public static WritingDTO toWritingDTO(WritingboardEntity writingboardEntity){
        WritingDTO writingDTO = new WritingDTO();

        System.out.println(writingboardEntity.getUser());

        //번호, 유저 번호, 게시글 내용, 작성일을 set
        writingDTO.setNo(writingboardEntity.getNo());
        writingDTO.setUserNo(writingboardEntity.getUserNo());
        writingDTO.setContent(writingboardEntity.getContent());
        writingDTO.setCreationDate(writingboardEntity.getCreationDate());
        writingDTO.setUser(writingboardEntity.getUser());
        writingDTO.setUserName(writingboardEntity.getUser().getUserName()); //조인한 것에서 UserName 받아옴

        return writingDTO;
    }

    //게시글을 저장하는 DTO
    public static WritingDTO toWritingPostDTO(WritingboardEntity writingboardEntity){
        WritingDTO writingDTO = new WritingDTO();

        //번호, 유저 번호, 게시글 내용, 작성일을 set
        writingDTO.setNo(writingboardEntity.getNo());
        writingDTO.setUserNo(writingboardEntity.getUserNo());
        writingDTO.setContent(writingboardEntity.getContent());
        writingDTO.setCreationDate(writingboardEntity.getCreationDate());

        return writingDTO;
    }
}
