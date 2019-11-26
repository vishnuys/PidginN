var ws;
var username = "vishal"
function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
}

function connect() {
	ws = new WebSocket('ws://localhost:8080/' + username);
	ws.onmessage = function(data) {
		addToPage(data.data);
	}
	setConnected(true);
	
	
	ws.send("Hi!");
}

function disconnect() {
	if (ws != null) {
		ws.close();
	}
	setConnected(false);
	console.log("Websocket is in disconnected state");
}

function sendData() {
	var data = JSON.stringify({
		'user' : $("#send-text").val()
	})
	ws.send(data);
}

function addToPage(message) {
	console.log(message);
}

$(function() {
	$("form").on('submit', function(e) {
		e.preventDefault();
	});
	$(document).ready(function(e) {
		connect();
	})
	$("#send-btn").click(function() {
		sendData();
	});
});