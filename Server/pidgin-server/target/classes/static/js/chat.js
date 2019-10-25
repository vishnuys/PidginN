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
										username : 'h',
										name : 'Howard',
										lastMessage : "Yes, I'm on it. I'll send it as soon as I am done.",
										lastUpdated : '3 days ago',
										chatMessages : [
												{
													sender : 'h',
													receiver : 'm',
													message : 'यह तुरंत किए जाने की आवश्यकता है',
													translatedMessage : 'This needs to be done immediately',
												},
												{
													sender : 'm',
													receiver : 'h',
													message : "Yes, I'm on it. I'll send it as soon as I am done.",
													translatedMessage : 'हां, मैं इस पर कायम हूं। मैं जैसे ही करूंगा, भेज दूंगा।'
												} ]
									},
									{
										username : 'n',
										name : 'Manuel',
										lastMessage : "Yes, I'm on it. I'll send it as soon as I am done.",
										lastUpdated : '4 days ago',
										chatMessages : [
												{
													sender : 'n',
													receiver : 'm',
													message : "Esto debe hacerse de inmediato",
													translatedMessage : 'This needs to be done immediately',
												},
												{
													sender : 'm',
													receiver : 'n',
													message : "Yes, I'm on it. I'll send it as soon as I am done.",
													translatedMessage : 'Sí, estoy en eso. Lo enviaré tan pronto como termine.'
												} ]
									},
									{
										username : 'g',
										name : 'George',
										lastMessage : "Yes, I'm on it. I'll send it as soon as I am done.",
										lastUpdated : '5 days ago',
										chatMessages : [
												{
													sender : 'g',
													receiver : 'm',
													message : "Dies muss sofort erfolgen",
													translatedMessage : 'This needs to be done immediately',
												},
												{
													sender : 'm',
													receiver : 'g',
													message : "Yes, I'm on it. I'll send it as soon as I am done.",
													translatedMessage : 'Ja, ich bin dabei. Ich werde es senden, sobald ich fertig bin.'
												} ]
									},
									{
										username : 'k',
										name : 'Karen',
										lastMessage : "Yes, I'm on it. I'll send it as soon as I am done.",
										lastUpdated : '6 days ago',
										chatMessages : [
												{
													sender : 'k',
													receiver : 'm',
													message : "ಇದನ್ನು ತಕ್ಷಣ ಮಾಡಬೇಕಾಗಿದೆ",
													translatedMessage : 'This needs to be done immediately',
												},
												{
													sender : 'm',
													receiver : 'k',
													message : "Yes, I'm on it. I'll send it as soon as I am done.",
													translatedMessage : 'ಹೌದು, ನಾನು ಅದರ ಮೇಲೆ ಇದ್ದೇನೆ. ನಾನು ಮುಗಿದ ತಕ್ಷಣ ಅದನ್ನು ಕಳುಹಿಸುತ್ತೇನೆ.'
												} ]
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

							$scope.connect = function() {
								$scope.websocket = new WebSocket(
										'ws://localhost:8080/chat/'
												+ $scope.username);
								$scope.websocket.onmessage = function(data) {

								}
								$scope.loading = false;
							}

							$scope.changeUser = function(username) {
								$scope.currentUser = username;
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
												$scope.connections[index].lastMessage = msg;
												$scope.connections[index].lastUpdated = 'Today';
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