package com.project.fofo.controller;


import com.project.fofo.service.ChatService;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import jakarta.servlet.http.HttpServletRequest;

import com.project.fofo.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatGptController {
    private final ChatService chatService;

    @ResponseBody
    @RequestMapping(value = "/chatgptGeneration", method = RequestMethod.POST)
    public List<String> chatGpt(@RequestParam("koWord") String koWord) {
        List<String> sentenceList = new ArrayList<String>();

        String engWord = null;
        String pron = null;
        String ko = null;
        String eng = null;
        String answer = chatService.wordTranslation(koWord);
        System.out.println("answer 값:" + answer);

        String[] parts = answer.split("\\s*\\d+\\.\\s*");

        System.out.println("********" + Arrays.toString(parts) + "********");

        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].trim();
            if (part.isEmpty()) {
                continue;
            }

            switch (i % 4) {
                case 0:
                    eng = part;
                    break;
                case 1:
                    System.out.println("part의 값: " + part);
                    engWord = extractTextInSingleQuotes(part);
                    break;
                case 2:
                    pron = part;
                    break;
                case 3:
                    ko = part;
                    break;
            }
        }

        System.out.println("engWord 값: " + engWord);
        System.out.println("pron 값: " + pron);
        System.out.println("ko 값: " + ko);
        System.out.println("eng 값: " + eng);

        sentenceList.add(engWord);
        sentenceList.add(pron);
        sentenceList.add(ko);
        sentenceList.add(eng);

        return sentenceList;
    }

    private static String extractTextInSingleQuotes(String input) {
        int startQuote = input.indexOf("'");
        int endQuote = input.lastIndexOf("'");
        if (startQuote >= 0 && endQuote >= 0 && startQuote < endQuote) {
            return input.substring(startQuote + 1, endQuote).trim();
        } else {
            return input;
        }
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
