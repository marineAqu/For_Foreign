package com.project.fofo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Words")
public class WordsEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long no; //단어 번호

    @Column
    private String koWord; //한글 단어

    @Column
    private String enWord; //영단어

    @Column
    private String pronSymbol; //발음 기호

    @Column
    private String koSentence; //한글 예문

    @Column
    private String enSentence; //영어 예문

    @Column
    private Long vocaNo; //단어장 번호

    @Column
    private char partSpeech; //품사

    @Column
    private char checkStatus; //사용자 체크

    @Column
    private char checkQuiz; //사용자에게 보이지 않는 체크
}
