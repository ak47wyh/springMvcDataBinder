function getXMLHttpRequest() {
    var xhr;
    if(window.ActiveXObject) {
        xhr= new ActiveXObject("Microsoft.XMLHTTP");
    }else if (window.XMLHttpRequest) {
        xhr= new XMLHttpRequest();
    }else {
        xhr= null;
    }
    return xhr;
}

function sajax() {
    var xhr = getXMLHttpRequest();
    xhr.open("post","url");
    var data = "name=mikan&address=street...";
    xhr.send(data);
    xhr.onreadystatechange= function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            alert("returned:"+ xhr.responseText);
        }
    };
}