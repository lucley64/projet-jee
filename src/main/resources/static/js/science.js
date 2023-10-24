let lens;
let result;
let image;

function openTab(elem) {
    const tab = elem.innerHTML;
    const tabs = document.querySelectorAll("div.tab");
    tabs.forEach(tab => tab.hidden = true);
    const buttons = document.querySelectorAll("div#tab-buttons>button.menu");
    buttons.forEach(btn => {
        btn.className = "menu";
    });
    elem.className += " active";
    const openTab = document.getElementById(tab);
    openTab.hidden = false;
    window.parent?.history.replaceState(null, window.parent?.document.title, "?menu=La science&cat=" + tab);
}

let stock = true;
function showStock() {
    stock = !stock;
    const colStocks = document.querySelectorAll(".qte");
    colStocks.forEach(elem => elem.hidden = stock);
}

function addCmd(elem) {
    const inp = elem.previousElementSibling;
    const qtestr = elem.parentElement?.parentElement?.parentElement?.getElementsByClassName("qte")[0].textContent
    if (qtestr && parseInt(inp.value) < parseInt(qtestr))
        inp.value = parseInt(inp.value) + 1 + "";
    const btn = inp.previousElementSibling;
    btn.disabled = false;
    if (qtestr && parseInt(inp.value) === parseInt(qtestr)) {
        elem.disabled = true;
    }
}

function remCmd(elem) {
    const inp = elem.nextElementSibling;
    if (parseInt(inp.value) > 0)
        inp.value = parseInt(inp.value) - 1 + "";
    const btn = inp.nextElementSibling;
    btn.disabled = false;
    if (parseInt(inp.value) === 0) {
        elem.disabled = true;
    }
}

function moveLens(a, ev) {
    ev.preventDefault();
    if (a instanceof HTMLImageElement)
        image = a;
    lens.hidden = false;
    result.hidden = false;
    result.style.backgroundImage = "url('" + image.src + "')";
    const cx = result.offsetWidth / lens.offsetWidth;
    const cy = result.offsetHeight / lens.offsetHeight;
    result.style.backgroundSize = (image.width * cx) + "px " + (image.height * cy) + "px";
    const pos = getCursorPos(image, ev);
    let x = pos.x - lens.offsetWidth / 2;
    let y = pos.y - lens.offsetHeight / 2;
    if (x > image.width - lens.offsetWidth) x = image.width - lens.offsetWidth;
    if (x < 0) x = 0;
    if (y > image.height - lens.offsetHeight) y = image.height - lens.offsetHeight;
    if (y < 0) y = 0;
    result.style.backgroundPosition = "-" + (x * cx) + "px -" + (y * cy) + "px";
    result.style.top = ev.clientY - result.offsetHeight - 10 + "px";
    result.style.left = ev.clientX + 10 + "px";
    x += image.x;
    y += image.y;
    lens.style.left = x + "px";
    lens.style.top = y + "px";
}

function getCursorPos(elem, ev) {
    const a = elem.getBoundingClientRect();
    let x = ev.pageX - a.left;
    let y = ev.pageY - a.top;
    x = x - window.scrollX;
    y = y - window.scrollY;
    return { x: x, y: y };

}

function term() {
    lens.hidden = true;
    result.hidden = true;
}

window.onload = () => {
    lens = document.getElementById("zoomLens");
    lens.addEventListener("mousemove", moveLens);
    lens.addEventListener("mouseleave", term);
    result = document.getElementById("zoomImage");
    const imgs = document.querySelectorAll(".image");
    imgs.forEach(image => {
        image.addEventListener("mousemove", moveLens);
    });
    const a = document.querySelector("#this-btn");
    ((a != null ? a : document.querySelector("#tab-buttons > button:nth-child(1)"))).click();
}

function ajouterAuPanier(elem) {
    const contDiv = elem.previousElementSibling;
    const inp = contDiv.querySelector("input.counter");
    const qtestr = elem.parentElement?.parentElement?.getElementsByClassName("qte")[0].textContent;
    const elemP = elem.parentElement?.parentElement?.getElementsByClassName("name")[0];
    if (parseInt(inp.value) > 0 && parseInt(inp.value) <= parseInt(qtestr)) {
        const xhr = new XMLHttpRequest();
        xhr.open("POST", "./php/panier.php", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = (ev) => {
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200)
                window.location.href = "";
            if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 401)
                alert("Vous devez vous connecter pour acceder au panier")

        }
        xhr.send(JSON.stringify({ "produit": { "id": elemP.id, "nom": elem.name, "img": "a", "qte": inp.value } }));

    }

}

function togglePanier(elem) {
    const div = elem.nextElementSibling;
    div.hidden = !div.hidden;
}

function viderPanier(elem) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', "./php/viderPanier.php", true);
    xhr.send();
    xhr.onreadystatechange = (ev) => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200)
            window.location.href = "";
    }
}

function commander(elem) {
    const xhr = new XMLHttpRequest();
    xhr.open('GET', "./php/majStock.php", true);
    xhr.send();
    xhr.onreadystatechange = (ev) => {
        if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
            alert("Commande validée");
            window.location.href = "";
        }
    }
}