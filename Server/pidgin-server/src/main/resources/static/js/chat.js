var app = angular.module('OOAD', []);
app
		.controller(
				'pidgin',
				[
						'$scope',
						'$http',
						function($scope, $http) {
							$scope.loading = true;
							$scope.username = getCookie('username');
							$scope.websocket = '';
							$scope.connections = [
									{
										username : 'g',
										name : 'Howard',
										lastMessage : 'PLEASE COMPLETE IT SOON',
										lastUpdated : '3 days ago',
										chatMessages : [
												{
													sender : 'g',
													receiver : 'm',
													message : 'Esto debe hacerse de inmediato',
													translatedMessage : 'This needs to be done immediately',
												},
												{
													sender : 'm',
													receiver : 'g',
													message : "Yes, I'm on it. I'll send it as soon as I am done.",
													translatedMessage : 'Sí, estoy en eso. Lo enviaré tan pronto como termine.'
												} ]
									},
									{
										username : 'n',
										name : 'Barney',
										lastMessage : 'PLEASE COMPLETE IT SOON',
										lastUpdated : '4 days ago'
									},
									{
										username : 'joey',
										name : 'Joey',
										lastMessage : 'PLEASE COMPLETE IT SOON',
										lastUpdated : '5 days ago'
									},
									{
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
										return c.substring(name.length,
												c.length);
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
										'ws://localhost:8080/chat/'
												+ $scope.username);
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
								console
										.log("Websocket is in disconnected state");
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
								$
										.ajax({
											url : '/translate',
											type : 'POST',
											data : {
												'sender' : $scope.username,
												'receiver' : $scope.currentUser,
												'text' : msg
											},
											success : function(data) {
												var data = {
													sender : $scope.username,
													receiver : $scope.currentUser,
													message : msg,
													translatedMessage : data
												}
												var index = $scope
														.getUserIndex($scope.currentUser);
												$scope.connections[index].chatMessages
														.push(data);
												$("#chatbox")
														.animate(
																{
																	scrollTop : $(
																			'#chatbox')
																			.get(
																					0).scrollHeight
																}, 1000);
												$('#send-text').val('');
												$scope.$digest();
											},
											error : function(e) {
												console.error(e)
											}
										});
							}

							$scope.print = function(msg) {
								console.log(msg);
							}

							$scope.logout = function() {
								document.cookie = "username=;expires=Thu; 01 Jan 1970"
								location.href = '/login';
							}

						} ]);