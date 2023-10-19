package com.project.fofo.DTO;


import com.project.fofo.entity.WordsEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizDTO {
    private Long no; //단어 번호
    private String koWord; //한글 단어
    private String enWord; //영단어
    private String pronSymbol;
    private String koSentence;
    private String enSentence;
    private Long vocaNo;
    private char partSpeech;
    private char checkStatus;
    private char checkQuiz;

    public static QuizDTO toQuizDTO(WordsEntity wordsEntity){
        QuizDTO quizDTO = new QuizDTO();

        //영단어, 한국어 단어, 체크 여부를 set
        quizDTO.setNo(wordsEntity.getNo());
        quizDTO.setKoWord(wordsEntity.getKoWord());
        quizDTO.setEnWord(wordsEntity.getEnWord());
        quizDTO.setPronSymbol(wordsEntity.getPronSymbol());
        quizDTO.setKoSentence(wordsEntity.getKoSentence());
        quizDTO.setEnSentence(wordsEntity.getEnSentence());
        quizDTO.setVocaNo(wordsEntity.getVocaNo());
        //quizDTO.setPartSpeech(wordsEntity.getPartSpeech());
        quizDTO.setCheckStatus(wordsEntity.getCheckStatus());
        quizDTO.setCheckQuiz(wordsEntity.getCheckQuiz());

        return quizDTO;
    }
}
