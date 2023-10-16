package com.project.fofo.controller;

import com.project.fofo.DTO.MemberDTO;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.repository.MemberRepository;
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

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/main")
    public String mainPage() {
        return "Main";
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

        if (password.isBlank()) {
            findMember.setUserName(name);
            findMember.setPassword(passwordEncoder.encode(password));
            memberRepository.save(findMember);
            return "redirect:/myPage";
        } else {
            if (password.length() < 8) {
                model.addAttribute("passwordError", "The password must be more than 8 characters.");
                System.out.println("비밀번호가 너무 짧습니다.");
                return "redirect:/myPage/modify";
            }
            findMember.setUserName(name);
            findMember.setPassword(passwordEncoder.encode(password));
            memberRepository.save(findMember);
            return "redirect:/myPage";
        }
    }

    @GetMapping("/overallRank")
    public String overallRank() {
        return "OverallRank";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "Join";
    }

    @PostMapping("/join")
    public String join(MemberDTO memberDTO, Model model) {
        MemlistEntity findMember = memberRepository.findByUid(memberDTO.getUid());

        if (memberDTO.getPassword().length() < 8) {
            model.addAttribute("passwordError", "The password must be more than 8 characters.");
            return "join";
        } else if (findMember != null) {
            model.addAttribute("duplicateID", "Duplicate ID");
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
