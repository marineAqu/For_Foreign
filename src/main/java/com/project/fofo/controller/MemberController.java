package com.project.fofo.controller;

/**
 * 파일명: MemberController
 * 작성자: 김민정
 **/

import com.project.fofo.DTO.MemberDTO;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.entity.TodaytopicEntity;
import com.project.fofo.repository.MemberRepository;
import com.project.fofo.repository.TodaytopicRepository;
import com.project.fofo.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TodaytopicRepository todaytopicRepository; //김도연 추가

    @GetMapping("/home")
    public String homePage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        model.addAttribute("user", user);

        //이하 김도연 작성: 날짜를 계산하여 하루 단위로 다른 오늘의 주제를 띄움
        long daysDifference = ChronoUnit.DAYS.between(LocalDate.of(2023, 11, 19), LocalDate.now());

        TodaytopicEntity todaytopicEntity= todaytopicRepository.findByNo(daysDifference);
        model.addAttribute("topic", todaytopicEntity.getTopic());

        return "Home";
    }

    @GetMapping("/myPage")
    public String myPage(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        model.addAttribute("user", user);
        return "MyPage";
    }

    @GetMapping("/myPage/modify")
    public String MyPageModifyForm(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        model.addAttribute("user", user);
        return "MyPageModify";
    }

    @PostMapping("/myPage/modify")
    @RequestMapping("/myPage/modify")
    public String modify(HttpServletRequest httpServletRequest, Principal principal, Model model) {
        String uid = principal.getName();

        MemlistEntity findMember = memberService.findByMember(uid);

        String name = httpServletRequest.getParameter("name");
        String password = httpServletRequest.getParameter("password");

        MemlistEntity findUserName = memberRepository.findByUserName(name);

        if (password.isBlank()) {
            if (findUserName != null) {
                model.addAttribute("duplicateName", "Duplicate Name");
                return "redirect:/myPage/modify";
            }
            findMember.setUserName(name);
            memberRepository.save(findMember);
            return "redirect:/myPage";
        } else {
            if (password.length() < 8) {
                model.addAttribute("passwordError", "The password must be more than 8 characters.");
                return "redirect:/myPage/modify";
            }
            if (findUserName != null) {
                if (!name.equals(findMember.getUserName())) {
                    model.addAttribute("duplicateName", "Duplicate Name");
                    return "redirect:/myPage/modify";
                }
            }
            findMember.setUserName(name);
            findMember.setPassword(passwordEncoder.encode(password));
            memberRepository.save(findMember);
            return "redirect:/myPage";
        }
    }

    @GetMapping("/join")
    public String joinPage() {
        return "Join";
    }

    @PostMapping("/join")
    public String join(MemberDTO memberDTO, Model model) {
        MemlistEntity findUid = memberRepository.findByUid(memberDTO.getUid());
        MemlistEntity findUserName = memberRepository.findByUserName(memberDTO.getUserName());

        if (memberDTO.getPassword().length() < 8) {
            model.addAttribute("passwordError", "The password must be more than 8 characters.");
            return "join";
        } else if (findUid != null) {
            model.addAttribute("duplicateID", "Duplicate ID");
            return "join";
        } else if (findUserName != null) {
            model.addAttribute("duplicateName", "Duplicate Name");
            return "join";
        } else {
            memberService.saveMember(memberDTO);
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/login";
    }
}
