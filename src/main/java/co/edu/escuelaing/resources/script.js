console.log('Script loaded');

function loadCurrentTime() {
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("timeResult").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/app/current-time");
    xhttp.send();
}

function loadHello() {
    let nameVar = document.getElementById("textInputHello").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("helloResult").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/app/hello?name=" + nameVar);
    xhttp.send();
}

function loadPi() {
    let decimals = document.getElementById("textInputPi").value || 2;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("piResult").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/app/pi?decimals=" + decimals);
    xhttp.send();
}

function loadRandom() {
    let min = document.getElementById("minValue").value || 0;
    let max = document.getElementById("maxValue").value || 1;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        document.getElementById("randomResult").innerHTML = this.responseText;
    }
    xhttp.open("GET", "/app/random?min=" + min + "&max=" + max);
    xhttp.send();
}