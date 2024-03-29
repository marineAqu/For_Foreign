package com.project.fofo.entity;

/**
 * 파일명: VocalistEntity
 * 작성자: 김도연
 **/

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Vocalist")
public class VocalistEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long no; //단어장 번호

    @Column
    private String bookTitle; //단어장 이름

    @Column
    private Long userNo; //유저 번호

    /**
     * 함수: toVocaEntity
     * 작성자: 김도연
     * 설명: 단어장 저장을 위한 함수. 단어장 제목을 받고 나머지는 임시로 하드코딩함.
     * */
    public static VocalistEntity toVocaEntity(String newVocaTit, Long userNo) {
        VocalistEntity vocalistEntity = new VocalistEntity();
        vocalistEntity.setBookTitle(newVocaTit);
        //유저 번호는 일단 하드코딩함 => 1106 하드코딩X 수정
        vocalistEntity.setUserNo(userNo);
        return vocalistEntity;
    }
}
