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
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "boardNo", referencedColumnName = "no", insertable = false, updatable = false)
    private List<BoardlikeEntity> boardlikeEntities;

    /**
     * 함수: toPostWriting
     * 작성자: 김도연
     * 설명: 작문 게시글 저장을 위한 함수. 게시글 내용, 날짜를 받음
     * */
    public static WritingboardEntity toPostWriting(Long userNo, String topicContent, Date date) {
        WritingboardEntity writingboardEntity = new WritingboardEntity();

        writingboardEntity.setUserNo(userNo);
        writingboardEntity.setContent(topicContent);
        writingboardEntity.setCreationDate(date);

        return writingboardEntity;
    }
}
