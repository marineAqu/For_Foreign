package com.project.fofo.entity;

/**
 * 파일명: WordsEntity
 * 작성자: 김도연
 **/

import com.project.fofo.DTO.QuizDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Setter
@Getter
@Table(name = "Words")
@Data
@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@ToString
public class WordsEntity {
    public WordsEntity() { }
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
    private char checkQuiz;  //사용자에게 보이지 않는 체크

    public Long getNo() {
       return no;
    }

    public void setVocaNo(Long vocaNo) {
        this.vocaNo = vocaNo;
    }

    public void setPartSpeech(char  partSpeech) {
        this.partSpeech = partSpeech;
    }

    public WordsEntity toEntity() {

        WordsEntity build = WordsEntity.builder()
                        .no(no)
                        .koWord(koWord)
                        .enWord(enWord)
                        .partSpeech(partSpeech)
                        .pronSymbol(pronSymbol)
                        .koSentence(koSentence)
                        .enSentence(enSentence)
                        .checkStatus('n')
                        .checkQuiz('n')
                        .build();
                return build;
    }

    /**
     * 함수: toQuizStates
     * 작성자: 김도연
     * 설명: 퀴즈에서 checkQuiz 필드의 상태를 변경하기 위한 함수
     * */
    public static WordsEntity toQuizStates(QuizDTO quizDTO) {
        WordsEntity wordsEntity = new WordsEntity();
        wordsEntity.setNo(quizDTO.getNo());
        wordsEntity.setKoWord(quizDTO.getKoWord());
        wordsEntity.setEnWord(quizDTO.getEnWord());
        wordsEntity.setPartSpeech(quizDTO.getPartSpeech());
        wordsEntity.setPronSymbol(quizDTO.getPronSymbol());
        wordsEntity.setEnSentence(quizDTO.getEnSentence());
        wordsEntity.setKoSentence(quizDTO.getKoSentence());
        wordsEntity.setVocaNo(quizDTO.getVocaNo());
        wordsEntity.setCheckStatus(quizDTO.getCheckStatus());
        wordsEntity.setCheckQuiz(quizDTO.getCheckQuiz());
        return wordsEntity;
    }

    /**
     * 함수: toSaveTransWord
     * 작성자: 김도연
     * 설명: 번역탭, 카메라탭에서 뜨는 단어 리스트를 단어장에 추가할 때 사용
     * */
    public static WordsEntity toSaveTransWord(Long vocaNo, String koWord, String enWord) {
        WordsEntity wordsEntity = new WordsEntity();

        wordsEntity.setKoWord(koWord);
        wordsEntity.setEnWord(enWord);
        wordsEntity.setVocaNo(vocaNo);
        wordsEntity.setCheckStatus('n');
        wordsEntity.setCheckQuiz('n');
        wordsEntity.setEnSentence(""); //퀴즈에서 예문이 비었는지 검사할 때 편의를 위해 null이 아닌 블랭크 삽입
        wordsEntity.setKoSentence(""); //퀴즈에서 예문이 비었는지 검사할 때 편의를 위해 null이 아닌 블랭크 삽입
        wordsEntity.setPronSymbol(""); //퀴즈에서 예문이 비었는지 검사할 때 편의를 위해 null이 아닌 블랭크 삽입
        return wordsEntity;
    }
}
