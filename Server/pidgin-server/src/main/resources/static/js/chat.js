var app = angular.module('OOAD', []);
app.controller('pidgin', function($scope) {

	$scope.loading = true;
	$scope.username = getCookie('username');
	$scope.websocket = '';
	
	function getCookie(cname) {
	  var name = cname + "=";
	  var ca = document.cookie.split(';');
	  for(var i = 0; i < ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ') {
	      c = c.substring(1);
	    }
	    if (c.indexOf(name) == 0) {
	      return c.substring(name.length, c.length);
	    }
	  }
	  return "";
	}

	$(document).ready(function() {
		if($scope.username == '') {
			location.href = '/login';
		}
		connect();
	});

	function setConnected(connected) {
		$("#connect").prop("disabled", connected);
		$("#disconnect").prop("disabled", !connected);
	}

	function connect() {
		$scope.websocket = new WebSocket('ws://localhost:8080/chat/' + $scope.username);
		$scope.websocket.onmessage = function(data) {
			addToPage(data.data);
		}
		setConnected(true);
	}

	function disconnect() {
		if (ws != null) {
			$scope.websocket.close();
		}
		setConnected(false);
		console.log("Websocket is in disconnected state");
	}

	function sendData() {
		var data = JSON.stringify({
			'user' : $("#send-text").val()
		})
		$scope.websocket.send(data);
	}

	function addToPage(message) {
		console.log(message);
	}

});