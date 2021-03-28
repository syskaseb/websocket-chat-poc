let client = null

function showMessage(user, value) {
    let newResponse = document.createElement('p')
    newResponse.appendChild(document.createTextNode(user + ': ' + value))
    let response = document.getElementById('response')
    response.appendChild(newResponse)
}

function connect() {
    const protocol = location.protocol.replace('http', 'ws')
    const url = protocol + "//" + document.domain + ":" + 31112 + "/chat"
    console.log("Connecting to url: " + url)
    client = Stomp.client(url)
    client.connect({}, function () {
        console.log('connected')
        client.subscribe("/topic/messages", function (message) {
            console.log('message received: ' + message.body)
            let json = JSON.parse(message.body)
            showMessage(json["user"], json["message"])
        })
    })
}

function sendMessage() {
    const user = document.getElementById('user').value
    const messageToSend = document.getElementById('messageToSend').value
    client.send("/app/chat", {}, JSON.stringify({'user': user, 'message': messageToSend}))
}