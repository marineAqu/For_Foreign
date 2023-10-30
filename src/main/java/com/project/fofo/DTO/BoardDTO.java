package com.project.fofo.DTO;

/**
 * 파일명: BoardDTO
 * 작성자: 김현지
 **/

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long no;
    private String koWord;
    private String enWord;
    private char  partSpeech;
    private String pronSymbol;
    private String koSentence;
    private String enSentence;
    private char checkStatus;
    private char checkQuiz;


}
