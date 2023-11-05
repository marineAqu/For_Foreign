package com.project.fofo.controller;

import com.project.fofo.service.WritingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;

/**
 * 파일명: WritingController
 * 작성자: 김도연
 **/

@Controller
@RequiredArgsConstructor
public class WritingController {
    private final WritingService writingService;
    long miliseconds = System.currentTimeMillis();
    Date date = new Date(miliseconds);

    @GetMapping("/WritingBoard")
    public String WritingBoard(Model model){
        model.addAttribute("Writingboard", writingService.SearchWritingPosts(date));
        System.out.println("오늘의 날짜: "+date);
        return "WritingBoard";
    }

    @PostMapping("saveWritingTop")
    public @ResponseBody void saveWritingTop(@RequestParam("inputValue") String inputValue) {
        //로그인 정보를 세션으로 넘기는지 쿠키로 넘기는지 몰라서 이 부분은 임시로 하드코딩함

        System.out.println("saveVocaTit 함수 들어옴 (컨트롤러)");

        writingService.saveWritingTop(inputValue, date);
        //TODO: 유저 넘버 로직 추가
    }
}
