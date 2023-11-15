package com.project.fofo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * 파일명: BoardlikeEntity
 * 작성자: 김도연
 **/

@Entity
@Setter
@Getter
@Table(name = "Boardlike")
public class BoardlikeEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long no; //번호
    //기본키가 필요없으므로 없이 하려고 했으나 jpa 오류로 인해 추가

    @Column
    private Long boardNo; //게시글 번호

    @Column
    private Long userNo; //유저 번호

    /**
     * 함수: toBoardlikeEntity
     * 작성자: 김도연
     * 설명: 좋아요 내역을 저장하기 위한 함수
     * */
    public static BoardlikeEntity toBoardlikeEntity(Long boardNo, Long userNo) {
        BoardlikeEntity boardlikeEntity = new BoardlikeEntity();

        boardlikeEntity.setBoardNo(boardNo);
        boardlikeEntity.setUserNo(userNo);

        return boardlikeEntity;
    }

}