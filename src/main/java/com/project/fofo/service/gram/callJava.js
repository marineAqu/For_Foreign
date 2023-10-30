//
// 파일명 : callJava
// 작성자 : 정채빈
// 설명 : 자바를 호출해준다
//

 function fetchRandomGrammar() {
        //자바호출
        fetch('/randgram')
            .then(response => response.text())
            .then(data => {
                document.getElementById("output1").innerHTML = data;
            })
            .catch(error => {
                console.error('Error:', error);
            });
        fetch('/examples')
                    .then(response => response.text())
                    .then(data => {
                        document.getElementById("output2").innerHTML = data;
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
    }

    document.addEventListener("DOMContentLoaded", fetchRandomGrammar);