 function fetchRandomGrammar() {
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