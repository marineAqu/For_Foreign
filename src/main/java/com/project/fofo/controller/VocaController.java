package com.project.fofo.controller;

/**
 * 파일명: VocaController
 * 작성자: 김도연
 **/

import com.project.fofo.service.QuizService;
import com.project.fofo.service.VocaService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class VocaController {

    private final QuizService quizService;
    private final VocaService vocaService;

    @GetMapping("vocaList")
    public String vocaList(Model model){
        //단어장DB에서 사용자 아이디를 검색해서 해당 사용자의 단어장만 보여줌. 현재는 하드코딩
        model.addAttribute("bookLis", quizService.SearchVocaBook(1L));
        return "vocaList";
    }

    @PostMapping("saveVocaTit")
    public @ResponseBody Boolean saveVocaTit(@RequestParam("inputValue") String inputValue) {
        //로그인 정보를 세션으로 넘기는지 쿠키로 넘기는지 몰라서 이 부분은 임시로 하드코딩함

        System.out.println("saveVocaTit 함수 들어옴 (컨트롤러)");

        vocaService.saveVocaTit(inputValue);
        //TODO: 유저 넘버 로직 추가

        return true;
    }

}
