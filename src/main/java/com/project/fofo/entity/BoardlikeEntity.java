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

    @Column
    private Long boardNo; //게시글 번호

    @Column
    private Long userNo; //유저 번호

}