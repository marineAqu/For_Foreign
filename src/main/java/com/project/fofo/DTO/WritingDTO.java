package com.project.fofo.DTO;


import com.project.fofo.entity.BoardlikeEntity;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WritingboardEntity;
import jakarta.persistence.Column;
import lombok.*;

import java.sql.Date;
import java.util.List;

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
    private int heart; //"좋아요"가 actvie인지 아닌지 체크
    private MemlistEntity user; //member테이블에서 조인
    private List<BoardlikeEntity> boardlikeEntities; //boardlike 테이블에서 조인 (즉 여러개가 나올 수 있음)

    //게시글을 검색하는 DTO
    public static WritingDTO toWritingDTO(WritingboardEntity writingboardEntity, Integer userNo){
        WritingDTO writingDTO = new WritingDTO();

        System.out.println(writingboardEntity.getUser());

        //번호, 유저 번호, 게시글 내용, 작성일을 set
        writingDTO.setNo(writingboardEntity.getNo());
        writingDTO.setUserNo(writingboardEntity.getUserNo());
        writingDTO.setContent(writingboardEntity.getContent());
        writingDTO.setCreationDate(writingboardEntity.getCreationDate());
        writingDTO.setUser(writingboardEntity.getUser());
        writingDTO.setUserName(writingboardEntity.getUser().getUserName()); //조인한 것에서 UserName 받아옴

        //값이 있는지에 따라 setheart 설정
        //이 안에서 다시 쿼리를 써서 연결하면 오류는 안 나겠지만 그러면 시간 효율이 너무 떨어짐 최대한 boardlikeEntities만으로 해결해야 할 것 같음 => 해결
        writingDTO.setHeart(0);//일단 0으로 설정하고
        for (BoardlikeEntity boardlikeEntity : writingboardEntity.getBoardlikeEntities()) {
            if (boardlikeEntity.getUserNo().equals(userNo.longValue())) {
                writingDTO.setHeart(1); //존재하면 1로 바뀜
            }
        }

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
