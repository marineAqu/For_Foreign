package com.project.fofo.controller;

/**
 * 파일명: LSWQuizController
 * 작성자: 김현지
 **/

import com.project.fofo.DTO.QuizDTO;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.repository.QuizRepository;
import com.project.fofo.service.MemberService;
import com.project.fofo.service.QuizService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.*;

@Controller
@RequiredArgsConstructor
public class LSWQuizController {

    private final QuizRepository quizRepository;
    private final QuizService quizService;
    private final MemberService memberService;

    @GetMapping("/BookLisLSW")
    public String BookLis(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);


        model.addAttribute("bookLis", quizService.SearchVocaBook(user.getNo().longValue()));
        return "BookLis_LSW";
    }


    @GetMapping("LisSenWr")
    public String LisSenWr(@RequestParam("vocaNo") Long vocaNo,
                           @RequestParam(value = "quizNum", defaultValue = "-1") Long quizNum,
                           @RequestParam(value = "nowIndex", defaultValue= "-1" ) int nowIndex,
                           @RequestParam(value = "totIndex", defaultValue= "-1" ) int totIndex,
                           Model model, HttpSession session) {

        QuizDTO quizDTO;
        List<QuizDTO> quizList = new ArrayList<>();

        if (totIndex == -1) {
            quizList = quizService.findVocaList(vocaNo);
            //Collections.shuffle(quizList);
            totIndex = quizList.size();
            nowIndex = 0;
            quizNum = quizList.get(nowIndex).getNo();
        } else {
            quizList = quizService.findVocaList(vocaNo);
            quizNum = quizList.get(nowIndex).getNo();
        }

        nowIndex++;

        quizDTO = quizService.SearchByNo(quizNum);

        model.addAttribute("quizTitle", quizDTO.getKoSentence());
        model.addAttribute("nowIndex", nowIndex);
        model.addAttribute("totIndex", totIndex);
        model.addAttribute("vocaNo", vocaNo);
        model.addAttribute("quizNum", quizNum);

        // 여기서 사용자 입력값을 모델에 추가하지 않음

        return "LisSenWr";
    }




}
