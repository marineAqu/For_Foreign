package com.project.fofo.controller;

/**
 * 파일명: SpacingCorrectionController
 * 작성자: 김민정
 **/

import com.project.fofo.DTO.QuizDTO;
import com.project.fofo.repository.QuizRepository;
import com.project.fofo.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SpacingCorrectionController {
    private final QuizRepository quizRepository;
    private final QuizService quizService;

    @GetMapping("/spacingCorrectionList")
    public String spacingCorrectionList(Model model){
        model.addAttribute("bookLis", quizService.SearchVocaBook(1L));
        return "SpacingCorrectionList";
    }

    @GetMapping("/spacingCorrectionStart")
    public String spacingCorrectionStartForm(@RequestParam("vocaNo") Long vocaNo,
                           @RequestParam(value = "quizNum", defaultValue = "-1") Long quizNum,
                           @RequestParam(value = "nowIndex", defaultValue= "-1" ) int nowIndex,
                           @RequestParam(value = "totIndex", defaultValue= "-1" ) int totIndex,
                           Model model, HttpSession session){

        //int 리스트를 만들어서 맞는 것들의 번호, 틀린 것들의 번호로 나누어야 할 듯
        QuizDTO quizDTO;
        List<QuizDTO> quizList = new ArrayList<>(); //퀴즈 풀 단어 리스트

        //이하 인덱스에 대한 로직
        if(totIndex == -1) { //첫 문제인 경우
            quizList = quizService.findVocaList(vocaNo); //해당 단어장의 단어들 모두 가져오기
            totIndex = quizList.size();
            nowIndex = 0;
            quizNum = quizList.get(nowIndex).getNo(); //1번째는 현재 했으니 2번째 단어로
        }
        else { //첫 문제가 아닌 경우
            quizList = quizService.findVocaList(vocaNo); //임시로이렇게해두겟는데 알고리즘 수정해야할듯함
            quizNum = quizList.get(nowIndex).getNo();
            System.out.println("else문의 quizNum에서 outOfBound 오류가 남 대체왜:" + quizNum);
        }

        nowIndex++;

        //테스트용
        quizDTO = quizService.SearchByNo(quizNum);

        List<Long> idList = quizService.findIdList(); //모든 단어 번호를 찾아 저장
        //0924 주석처리: get으로 id 찾으면 되니까 딱히 필요 없을거라고 생각했는데 오답처리 로직때문에 잇어야할듯 시간 좀 걸릴까봐 걱정

        model.addAttribute("corIndex", quizDTO.getKoSentence());
        //Q, 퀴즈 인덱스, 답안 버튼 보이기
        model.addAttribute("nowIndex", nowIndex);
        model.addAttribute("totIndex", totIndex);
        model.addAttribute("vocaNo", vocaNo);
        model.addAttribute("enSentence", quizDTO.getEnSentence());
        model.addAttribute("koSentence", quizDTO.getKoSentence());
        model.addAttribute("quizNum", quizNum);

        return "SpacingCorrectionStart";
    }

    @PostMapping("spacingCorrectionStart")
    public String spacingCorrectionStart(@RequestParam("quizNum") Long quizNum, HttpSession session, Model model) {
        session.setAttribute("quizNum", quizNum);
        return "SpacingCorrectionStart";
    }
}
