package com.project.fofo.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatgptService chatgptService;

    public String getChatResponse(String prompt) {
        // ChatGPT 에게 질문을 던집니다.
        return chatgptService.sendMessage(prompt);
    }

    public String koreanGeneration(String prompt) {
        String charToRemove = "\"_";

        String request = prompt + "이란 단어를 넣은 짧은 한국어 문장을 만들어 주세요.";
        //String request = "다음 단어를 넣은 짧은 한국어 문장을 만들어 주세요: " + prompt;
        String responseKo = chatgptService.sendMessage(request).trim();
        String resultResponseKo = responseKo.replaceAll(charToRemove, "");

        return resultResponseKo;
    }

    public String koreanTransation (String prompt) {
        String charToRemove = "\"";

        String request = prompt + "를 영어로 번역해 주세요";
        String responseEng = chatgptService.sendMessage(request).trim();
        String resultResponseEng = responseEng.replaceAll(charToRemove, "");

        return resultResponseEng;
    }

    public String koreanSpelling (String prompt) {
        String charToRemove = "\"";

        //String request = "다음 문장의 맞춤법을 검사해 주세요.: " + prompt;
        String request = "다음 문장의 맞춤법이 맞으면 그대로 두고 틀리면 알맞게 고쳐 주세요: " + prompt;
        String response = chatgptService.sendMessage(request).trim();
        String resultResponse = response.replaceAll(charToRemove, "");

        return resultResponse;
    }
}
