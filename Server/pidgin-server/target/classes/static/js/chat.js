var app = angular.module('OOAD', []);
app
		.controller(
				'pidgin',
				function($scope) {

					$scope.loading = true;
					$scope.username = getCookie('username');
					$scope.websocket = '';
					$scope.connections = [
							{
								username : 'howard',
								name : 'Howard',
								lastMessage : 'PLEASE COMPLETE IT SOON',
								lastUpdated : '3 days ago',
								chatMessages : [
										{
											username : 'howard',
											message : 'This needs to be done immediately',
											translatedMessage : 'Esto debe hacerse de inmediato'
										},
										{
											username : 'a',
											message : "Yes, I'm on it. I'll send it as soon as I am done.",
											translatedMessage : 'Sí, estoy en eso. Lo enviaré tan pronto como termine.'
										} ]
							}, {
								username : 'barney',
								name : 'Barney',
								lastMessage : 'PLEASE COMPLETE IT SOON',
								lastUpdated : '4 days ago'
							}, {
								username : 'joey',
								name : 'Joey',
								lastMessage : 'PLEASE COMPLETE IT SOON',
								lastUpdated : '5 days ago'
							}, {
								username : 'michael',
								name : 'Michael',
								lastMessage : 'PLEASE COMPLETE IT SOON',
								lastUpdated : '6 days ago'
							} ];

					$scope.currentUser = $scope.connections[0].username;

					function getCookie(cname) {
						var name = cname + "=";
						var ca = document.cookie.split(';');
						for (var i = 0; i < ca.length; i++) {
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

					$scope.ready = function() {
						if ($scope.username == '') {
							location.href = '/login';
						}
						$scope.connect();
					};

					function setConnected(connected) {
						$("#connect").prop("disabled", connected);
						$("#disconnect").prop("disabled", !connected);
					}

					$scope.connect = function() {
						$scope.websocket = new WebSocket(
								'ws://localhost:8080/chat/' + $scope.username);
						$scope.websocket.onmessage = function(data) {
							addToPage(data.data);
						}
						setConnected(true);
						$scope.loading = false;
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

					$scope.getUserIndex = function(username) {
						for ( var i in $scope.connections) {
							if ($scope.connections[i].username == username) {
								return i;
							}
						}
					};

					$scope.sendMessage = function(msg) {
						console.log($scope.messageToSend);
						var data = {
							username : $scope.username,
							message : msg,
							translatedMessage : 'Loading...'
						}
						var index = $scope.getUserIndex($scope.currentUser);
						$scope.connections[index].chatMessages.push(data);
						$('#send-text').val('');
					}

					$scope.print = function(msg) {
						console.log(msg);
					}

				});