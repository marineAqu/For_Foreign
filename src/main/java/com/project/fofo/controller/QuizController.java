package com.project.fofo.controller;

/**
 * 파일명: QuizController
 * 작성자: 김도연
 **/

import com.project.fofo.DTO.QuizDTO;
import com.project.fofo.entity.MemlistEntity;
import com.project.fofo.entity.VocalistEntity;
import com.project.fofo.entity.WordsEntity;
import com.project.fofo.repository.BoardRepository;
import com.project.fofo.repository.QuizRepository;
import com.project.fofo.service.MemberService;
import com.project.fofo.service.QuizService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class QuizController {
    private final QuizRepository quizRepository;
    private final QuizService quizService;
    private final MemberService memberService;
    private final BoardRepository boardRepository;

    @GetMapping("/BookLis")
    public String BookLis(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        //단어장DB에서 사용자 아이디를 검색해서 해당 사용자의 단어장만 보여줌
        model.addAttribute("bookLis", quizService.SearchVocaBook(user.getNo().longValue()));
        return "bookLis";
    }

    @GetMapping("/QuizList")
    public String QuizList(){
        return "QuizList";
    }

    @GetMapping("endOfQuiz")
    public String endOfQuiz(Model model, @RequestParam("vocaNo") Long vocaNo){
        //전체 문제, 맞은 개수
        List<WordsEntity> wordsEntity = quizRepository.findByVocaNo(vocaNo);

        //단어리스트
        model.addAttribute("boardList", boardRepository.findByVocaNo(vocaNo));

        //모달
        model.addAttribute("totNum", wordsEntity.size()); //총 개수
        model.addAttribute("correctNum", wordsEntity.stream().filter(entity -> String.valueOf(entity.getCheckQuiz()).equals("y")).count()); //맞은 개수
        return "endOfQuiz";
    }

    @GetMapping("quizStart")
    public String quizStart(@RequestParam("vocaNo") Long vocaNo,
                            @RequestParam(value = "quizNum", defaultValue = "-1") Long quizNum, //풀 단어장 번호와 단어 번호, 타이머
                            @RequestParam("setTimer") int setTimer,
                            @RequestParam(value = "nowIndex", defaultValue= "-1" ) int nowIndex,
                            @RequestParam(value = "totIndex", defaultValue= "-1" ) int totIndex,
                            Model model) {

        //int 리스트를 만들어서 맞는 것들의 번호, 틀린 것들의 번호로 나누어야 할 듯
        QuizDTO quizDTO;
        List<QuizDTO> quizList = new ArrayList<>(); //퀴즈 풀 단어 리스트

        //Long quizNum = 0L; //퀴즈 번호
        //int nowIndex; //전체 퀴즈 중 현재 인덱스
        //int totIndex; //전체 인덱스    0924(vocaNo 추가작업일자): model로 전달하다가 파라매터로 넘김

        //이하 인덱스에 대한 로직
        //첫 문제일 경우
        if(totIndex == -1) { //첫문제인 경우
            quizList = quizService.findVocaList(vocaNo); //해당 단어장의 단어들 모두 가져오기
            totIndex = quizList.size();
            nowIndex = 0;
            quizNum = quizList.get(nowIndex).getNo(); //1번째는 현재 했으니 2번째 단어로

            System.out.println(quizList);
            System.out.println("quizNum null 오류가 날 리가 없는디:" + quizNum);

            //검증용
            System.out.println("토탈인덱스: "+totIndex);
            System.out.println("나우인덱스: "+nowIndex);

            //session.setAttribute("setTimer", setTimer);
            //검증

            System.out.println("setTimer:"+ setTimer);
        }
        else {  //첫 문제가 아닌 경우
            quizList = quizService.findVocaList(vocaNo); //임시로이렇게해두겟는데 알고리즘 수정해야할듯함
            quizNum = quizList.get(nowIndex).getNo();
            System.out.println("else문의 quizNum에서 outOfBound 오류가 남 대체왜:" + quizNum);

            //totIndex = (int) session.getAttribute("totIndex");
            //nowIndex = (int) session.getAttribute("nowIndex") + 1;
        }
        //session.setAttribute("totIndex", totIndex);
        //session.setAttribute("nowIndex", nowIndex);
        //session.setAttribute("quizList", quizList);

        nowIndex++;

        //테스트용
        //System.out.println("get에서:"+session.getAttribute("quizNum"));
        System.out.println("get에서:"+quizNum);
        quizDTO = quizService.SearchByNo(quizNum);

        //List<Long> idList = quizService.findIdList(); //모든 단어 번호를 찾아 저장 -> 1118 주석처리 (다른 단어장, 다른 유저 거 가져오면 안됨)
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < quizList.size(); i++) idList.add(quizList.get(i).getNo()); // 1118 추가: 현재 단어장의 것만으로 오답 버튼 생성
        //0924 주석처리: get으로 id 찾으면 되니까 딱히 필요 없을거라고 생각했는데 오답처리 로직때문에 잇어야할듯 시간 좀 걸릴까봐 걱정

        //System.out.println("id리스트 검증:");
        //for (int i = 0; i < idList.length; i++) System.out.println("Index " + i + ": " + idList[i]); //검증

        //이하 정답 버튼에 대한 코드
        String[] ansList = new String[3];
        int corIndex; //ansList 배열에서 정답이 위치한 인덱스
        ansList[0] = quizDTO.getKoWord(); //정답

        //정답 제외 오답 2개 뽑아야 함
        idList.remove((Long) quizNum); //검증완료: 형변환 지우면 안됨

        //오답 두 문항을 추가하는 로직:
        Random random = new Random();
        int index1 = random.nextInt(idList.size());
        int index2;
        do {
            index2 = random.nextInt(idList.size()); // 두 번째 랜덤 인덱스 (서로 다른 값이어야 함)
        } while (index2 == index1);

        Long temp = idList.get(index1);
        ansList[1] = quizService.SearchByNo(temp).getKoWord();
        temp = idList.get(index2);
        ansList[2] = quizService.SearchByNo(temp).getKoWord();
        //답안은 사전 순으로 정렬 (즉 정답 위치 랜덤)
        Arrays.sort(ansList);

        //정답 위치 찾기: 로직 수정 필요(오류로 corIndex를 찾을 수 없는 else의 경우)
        if(ansList[0].equals(quizDTO.getKoWord())) corIndex = 0;
        else if(ansList[1].equals(quizDTO.getKoWord())) corIndex = 1;
        else corIndex = 2;
        //else System.out.println("corIndex 찾는 과정 중 오류 발생");
        System.out.println("corIndex:"+corIndex);
        model.addAttribute("corIndex", corIndex);
        //System.out.println("id리스트 삭제 잘 됐는지 검증:"+idList);

        //Q, 퀴즈 인덱스, 답안 버튼 보이기
        model.addAttribute("quizTitle", quizDTO.getEnWord());
        model.addAttribute("setTimer", setTimer);
        model.addAttribute("nowIndex", nowIndex);
        model.addAttribute("totIndex", totIndex);
        model.addAttribute("vocaNo", vocaNo);
        model.addAttribute("A1", ansList[0]);
        model.addAttribute("A2", ansList[1]);
        model.addAttribute("A3", ansList[2]);

        model.addAttribute("quizNum", quizNum);

        return "quizStart";
    }
    @PostMapping("startQuiz")
    public String startQuiz(@RequestParam("quizNum") Long quizNum,
                            HttpSession session, Model model) {

        session.setAttribute("quizNum", quizNum);
        System.out.println("post에서:"+quizNum);

        //return "quizStart?quizNum="+quizNum;
        return "quizStart";
    }

    @PostMapping("checkEmpty")
    public @ResponseBody Boolean checkEmpty(@RequestParam("vocaNo") Long vocaNo) {

        System.out.println("checkEmpty 함수에서 단어장에 단어 몇 개 들어있는지: "+boardRepository.findByVocaNo(vocaNo).size());

        if(boardRepository.findByVocaNo(vocaNo).size() < 3) return false;
        else return true;
    }

    @PostMapping("answerCheck")
    public @ResponseBody void answerCheck(@RequestParam("quizNum") Long quizNum, @RequestParam("tf") String tf,
                                          HttpSession session) {
        System.out.println("tf값: " + tf);

        QuizDTO quizDTO;
        quizDTO = quizService.SearchByNo(quizNum);
        System.out.println("엔서체크 컨트롤러에서 quizDTO의 getEnWord:" + quizDTO.getEnWord()); //setCh 오류로 인한 테스트

        if(tf.equals("O")) {
            quizDTO.setCheckQuiz('y');
            //y로 상태 변경
            quizRepository.save(WordsEntity.toQuizStates(quizDTO));
        }
        else if(tf.equals("X")) {
            quizDTO.setCheckQuiz('n');
            //n로 상태 변경
            quizRepository.save(WordsEntity.toQuizStates(quizDTO));
        }

        Long nowQuizNum = quizNum + 1;

        System.out.println("answerCheck컨트롤러에서 quizNum = " + nowQuizNum);
        System.out.println(session.getAttribute("totIndex"));
    }

    @PostMapping("studyPointWordQ")
    public @ResponseBody void studyPointWordQ(@RequestParam("correctNum") int correctNum) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uid = ((UserDetails) principal).getUsername();
        MemlistEntity user = memberService.findByMember(uid);

        quizService.givingPoint(user, 5, correctNum);
    }
}