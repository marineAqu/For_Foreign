package com.project.fofo.controller;

import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.service.MemberService;
import com.project.fofo.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 파일명: MoveController
 * 작성자: 정채빈
 **/
@Controller
@RequiredArgsConstructor //김도연 추가
public class MoveController {
    private final MemberService memberService; //김도연 추가
    private final QuizService quizService; //김도연 추가


    ///페이지 이동
    @GetMapping("nextcamera")
    public String nextcamera(){
        return "NextCamera";
    }

    @GetMapping("camera")
    public String cameraPage() {
        return "Camera";
    }

    @GetMapping("imgtrans")
    public String imgtransPage(Model model) {
        //imgtransPage 함수 코드 작성: 김도연
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        model.addAttribute("bookLis", quizService.SearchVocaBook(user.getNo().longValue()));

        return "Imgtrans";}
}
