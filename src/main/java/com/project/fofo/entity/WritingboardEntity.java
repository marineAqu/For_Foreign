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

    @ManyToOne
    @JoinColumn(name = "userNo", referencedColumnName = "no", insertable = false, updatable = false)
    private MemlistEntity user;

    /**
     * 함수: toPostWriting
     * 작성자: 김도연
     * 설명: 작문 게시글 저장을 위한 함수. 게시글 내용, 날짜를 받고 유저 번호는 임시로 하드코딩함.
     * */
    public static WritingboardEntity toPostWriting(String topicContent, Date date) {
        WritingboardEntity writingboardEntity = new WritingboardEntity();

        writingboardEntity.setUserNo(2L);
        writingboardEntity.setContent(topicContent);
        //유저 번호는 일단 하드코딩함
        //TOD O: 아래 코드 수정 필요(유저 번호 하드코딩)
        writingboardEntity.setCreationDate(date);

        return writingboardEntity;
    }
}
