"use strict";

var x, y, r;

var svg = document.getElementById("svg");

function drawPoint(x, y, r, result) {
    let circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", x * 60 * 2 / r + 150);
    circle.setAttribute("cy", -y * 60 * 2 / r + 150);
    circle.setAttribute("r", 3);
    circle.style.fill = result ? "#09a53d" : "#a50909";
    svg.appendChild(circle);
}


function setX(val) {
    document.getElementById("X").value = val
}

function transformSvgToPlane(svgX, svgY, r) {
    let planeX = (svgX - 150) / (120 / r);
    let planeY = (150 - svgY) / (120 / r);

    return {x: planeX, y: planeY};
}

function addToTable(x, y, r, result) {
    const table = document.getElementById("outputTable");
    const span = document.getElementById("no-results");
    if (span) {
        span.innerText = "";
        span.className = "notification";
    }

    const newRow = table.insertRow();
    newRow.insertCell().innerText = x;
    newRow.insertCell().innerText = y;
    newRow.insertCell().innerText = r;
    newRow.insertCell().innerHTML = result
        ? "<span>Попал</span>"
        : "<span>Промазал</span>";
}

async function checkPoint(x, y, r) {
    const form = new FormData();
    form.append("X", x);
    form.append("Y", y);
    form.append("R", r);
    form.append("action", "checkPoint")

    const url = "controller?" + new URLSearchParams(form).toString();
    const response = await fetch(url, {method: "get"});

    if (!response.ok) {
        createNotification("Не удалось отправить точку.");
    }

    const data = await response.json();
    if (data.error) createNotification(data.error);

    return data;
}

async function sendCoordinatesToServer(x, y, r) {
    const data = await checkPoint(x, y, r);

    if (!data.error) {
        drawPoint(x, y, r, data.result);
        addToTable(x, y, r, data.result);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("outputTable");

    if (table) {
        for (let item of table.rows) {
            const x = parseFloat(item.children[0].innerText.trim());
            const y = parseFloat(item.children[1].innerText.trim());
            const r = parseFloat(item.children[2].innerText.trim());
            if (isNaN(x) || isNaN(y) || isNaN(r)) continue;

            const result = item.children[3].innerText.trim() === "Попал";
            drawPoint(x, y, r, result);
        }
    }

    svg.addEventListener("click", (event) => {
        if (validateR()) {
            let point = svg.createSVGPoint();
            point.x = event.clientX;
            point.y = event.clientY;

            let ctm = svg.getScreenCTM();
            if (ctm) {
                let invertedCTM = ctm.inverse();
                let svgPoint = point.matrixTransform(invertedCTM);

                let planeCoords = transformSvgToPlane(svgPoint.x, svgPoint.y, r);
                console.log(planeCoords)
                sendCoordinatesToServer(
                    planeCoords.x.toFixed(1), planeCoords.y.toFixed(1), r
                );
            }
        }
    });
});

document.getElementById("checkButton").onclick = function () {
    console.log(x, y, r)
    if (validateX() && validateY() && validateR()) {
        const form = new FormData();
        form.append("X", x);
        form.append("Y", y);
        form.append("R", r);
        form.append("action", "submitForm")

        const url = "controller?" + new URLSearchParams(form).toString();

        window.location.href = url;
        /*
        const form = $('<form>', {
            action: "controller",
            method: "get"
        });

        const args = {action: "submitForm", X: x, Y: y, R: r};
        Object.entries(args).forEach(entry => {
            const [key, value] = entry;
            $('<input>').attr({
                type: "hidden",
                name: key,
                value: value
            }).appendTo(form);
        });
        form.appendTo('body').submit()
         */
    }
};

function createNotification(message) {
    let output = document.getElementById("error")

    if (document.getElementById("error")) {
        document.getElementById("error").remove()
    }

    let newNotification = document.createElement("h3")
    newNotification.setAttribute("id", "error-message")
    newNotification.innerHTML = "<span class='notification'>" + message + "</span>"
    output.prepend(newNotification)
}


function validateX() {
    x = document.getElementById("X").value
    console.log(x)
    if (isNumeric(x)) return true;
    else {
        createNotification("Значение X не выбрано");
        return false;
    }
}

function validateY() {
    y = document.querySelector("input[name=Y-input]").value.replace(",", ".")
    console.log(y)
    if (y === undefined) {
        createNotification("Y не введён");
        return false;
    } else if (!isNumeric(y)) {
        createNotification("Y не число");
        return false;
    } else if (y < -3 || y > 3) {
        createNotification("Y не входит в область допустимых значений");
        return false;
    } else return true;
}

function validateR() {
    try {
        r = document.querySelector("input[name=R-radio-group]:checked").value
        return true;
    } catch (err) {
        createNotification("Значение R не выбрано");
        return false;
    }
}

function isNumeric(value) {
    return ((value != null) &&
        (value !== '') &&
        !isNaN(Number(value.toString())));
}