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

<<<<<<< HEAD
        String request = prompt + "이란 단어를 넣은 짧은 한국어 문장을 만들어 주세요.";
=======
        String request = prompt + "이란 단어를 이용한 짧은 한국어 문장을 만들어 주세요.";
>>>>>>> 379c3bb ([추가] chat gpt api로 단어 번역, 발음 표기 설정 (2024.1.12 김현지))
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
<<<<<<< HEAD
}
=======

    public String wordTranslation (String prompt) {
        String charToRemove = "\"";

        String request = "1. '" +  prompt + "'" + "이 단어를 영어로 번역해 주세요 " + "2. '" +  prompt + "'" + " 이 단어의 한국어 발음 방법을 알파벳으로 알려주세요 " +
                "3. '" +  prompt + "'" + "이란 단어를 응용한 짧은 한국어 문장만 만들어 주세요. " + "4. 그 문장을 영어로 번역해 주세요" ;
        System.out.println("request: " + request);
        //System.out.println("chatgptService.sendMessage(request)의 값: " + chatgptService.sendMessage(request));
        String wordResponse = chatgptService.sendMessage(request).trim();
        //System.out.println("wordResponse 값: " + wordResponse);
        String resultWordResponse = wordResponse.replaceAll(charToRemove, "");
        //System.out.println("resultWordResponse 값: " + resultWordResponse);

        return resultWordResponse;
    }

}
>>>>>>> 379c3bb ([추가] chat gpt api로 단어 번역, 발음 표기 설정 (2024.1.12 김현지))
