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

function loadSum() {
    const minValue = parseInt(document.getElementById('minValue').value, 10);
    const maxValue = parseInt(document.getElementById('maxValue').value, 10);

    if (!isNaN(minValue) && !isNaN(maxValue)) {
        fetch(`/app/random?min=${minValue}&max=${maxValue}`)
            .then(response => response.text())
            .then(data => {
                document.getElementById('sumResult').innerText = data;
            })
            .catch(error => {
                console.error('Error:', error);
                document.getElementById('sumResult').innerText = 'An error occurred.';
            });
    } else {
        document.getElementById('sumResult').innerText = 'Please enter valid numbers.';
    }
}
