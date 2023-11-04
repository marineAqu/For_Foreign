package com.project.fofo.entity;

/**
 * 파일명: WritingboardEntity
 * 작성자: 김도연
 **/


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Setter
@Getter
@Table(name = "Writingboard")
public class WritingboardEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long no; //번호

    @Column
    private Long userNo; //유저 번호

    @Column
    private String content; //게시글 내용

    @Column
    private Date creationDate; //작성일

}
