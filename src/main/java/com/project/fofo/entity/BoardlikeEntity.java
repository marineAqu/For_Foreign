package com.project.fofo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

}