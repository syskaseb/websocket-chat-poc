let client = null

function showMessage(user, value) {
    let newResponse = document.createElement('p')
    newResponse.appendChild(document.createTextNode(user + ': ' + value))
    let response = document.getElementById('response')
    response.appendChild(newResponse)
}

function connect() {
    const protocol = location.protocol.replace('http', 'ws')
    const url = protocol + "//" + document.domain + ":" + location.port + "/games/tictactoe"
    console.log("Connecting to url: " + url)
    client = new WebSocket(url)
    client.onopen = () => {
        console.log('connected')
        client.send("Here's some text that the server is urgently awaiting!");
    }
    client.onmessage = function (event) {
        console.log('server message:  ' + event.data);
    }
}

function sendMessage() {
    const user = document.getElementById('user').value
    const messageToSend = document.getElementById('messageToSend').value
    // client.send("/app/chat", {}, JSON.stringify({'user': user, 'message': messageToSend}))
}