package com.project.fofo.controller;

import com.project.fofo.service.ChatService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatGptController {
    private final ChatService chatService;

    @ResponseBody
    @RequestMapping(value = "/chatgptGeneration", method = RequestMethod.POST)
    public List<String> chatGpt(@RequestParam("koWord") String koWord) {
        List<String> sentenceList = new ArrayList<String>();

        String ko = chatService.koreanGeneration(koWord);
        String eng = chatService.koreanTransation(ko);

        sentenceList.add(ko);
        sentenceList.add(eng);

        return sentenceList;
    }

    @ResponseBody
    @RequestMapping(value = "/chatgptSpelling", method = RequestMethod.POST)
    public List<String> chatGptSpelling(@RequestParam("koSentenceValue") String koSentenceValue) {
        List<String> sentenceList = new ArrayList<String>();

        String ko = chatService.koreanSpelling(koSentenceValue);
        String eng = chatService.koreanTransation(ko);

        sentenceList.add(ko);
        sentenceList.add(eng);

        return sentenceList;
    }
}
