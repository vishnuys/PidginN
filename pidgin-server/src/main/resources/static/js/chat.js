var app = angular.module('OOAD', []);
app.controller('pidgin', ['$scope', '$http', function($scope, $http) {
	$scope.loading = true;
	$scope.username = getCookie('username');
	$scope.lang = getCookie('language');
	$scope.loggedInUser = {
		'username': getCookie('username'),
		'userID': getCookie('userID'),
		'language': getCookie('language'),
		'firstName': getCookie('firstName'),
		'lastName': getCookie('lastName'),
		'contactNo': getCookie('contactNo')
	};
	$scope.websocket = '';
	$scope.userList = [];
	$scope.connections = [];
	$scope.vKeyboard = {
		'es': 'https://gate2home.com/Spanish-Keyboard',
		'en': 'https://gate2home.com/English-Keyboard',
		'hi': 'https://gate2home.com/Hindi-Keyboard',
		'de': 'https://gate2home.com/German-Keyboard',
		'fr': 'https://gate2home.com/French-Keyboard'
	}

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

	function setCookie(cname, cvalue, exdays=60) {
		var d = new Date();
		d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
		var expires = "expires="+d.toUTCString();
		document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	}

	function deleteCookie(cname) {
		document.cookie = cname + "=;expires=Thu; 01 Jan 1970";
	}

	$scope.ready = function() {
		if ($scope.username == '') {
			location.href = '/login';
		}
		$scope.connect();
	};

	$scope.onMessage = function(messageJson) {
		var index, uname;
		var msgJson = JSON.parse(messageJson.body);
		if($scope.loggedInUser.username == msgJson.senderUserName) {
			index = $scope.getUserIndex(msgJson.recieverUserName);
			uname = msgJson.recieverUserName;
		}
		else {
			index = $scope.getUserIndex(msgJson.senderUserName);
			uname = msgJson.senderUserName;
		}
		$scope.connections[index].chatMessages.push(msgJson);
		$scope.connections[index].lastMessage = msgJson.userMessage;
		$scope.connections[index].lastUpdated = 'Today';
		$scope.$digest();
		console.log(uname, $("#chatbox-"+uname).scrollTop())
		$("#chatbox-"+uname).animate({
			scrollTop : $("#chatbox-"+uname).scrollTop() + 10000
		}, 1000);
	}

	$scope.onFetchAllMessages = function(messageJson) {
		var msg = JSON.parse(messageJson.body);
		for (var i in msg) {
			var chatCount = msg[i].chatMessages.length;
			if (chatCount > 0) {
				msg[i]['lastMessage'] = msg[i].chatMessages[chatCount-1].userMessage;
				msg[i]['lastUpdated'] = 'Some time ago';
			}
		}
		$scope.connections = msg;
		if ($scope.connections.length > 0) {
			$scope.selectedUser = $scope.connections[0];
		}
		$scope.$digest();
		$(".chatbox").animate({
			scrollTop : $('.chatbox').scrollTop() + 10000
		}, 1000);
	}

	$scope.connect = function() {
		var current_url = location.origin;
		if(current_url.includes('?'))
			current_url = current_url.split('?')[0]
		if(current_url.split(':')[0]=='https')
			var websocket_url = current_url.replace(/https/, 'wss')
		else
			var websocket_url = current_url.replace(/http/, 'ws')
		if (websocket_url[websocket_url.length-1] == '/')
			websocket_url += 'chatroom'
		else if(websocket_url[websocket_url.length-1] == '#'){
			websocket_url = websocket_url.slice(0,-1)
			if (websocket_url[websocket_url.length-1] == '/')
				websocket_url += 'chatroom'
			else
				websocket_url += '/chatroom'
		}
		else
			websocket_url += '/chatroom'
		$scope.websocket = Stomp.client(websocket_url);
		$scope.websocket.connect({
			'username': $scope.loggedInUser.username
		}, function() {
			var userInfo = {
				'username': $scope.loggedInUser.username,
				'userID': $scope.loggedInUser.userID
			};
			$scope.websocket.subscribe("/topic/chatroom", function(message) {
				console.log(message);
			});
			$scope.websocket.subscribe("/user/queue/private", $scope.onMessage);
			$scope.websocket.subscribe("/user/queue/allmessages", $scope.onFetchAllMessages);
			$scope.websocket.send("/app/sendall", {}, JSON.stringify(userInfo));
		}, function(error) {
			console.log(error);
		});
		$scope.loading = false;
	}

	$scope.changeUser = function(username) {
		var index = $scope.getUserIndex(username);
		$scope.selectedUser = $scope.connections[index];
	}

	$scope.getUserIndex = function(username) {
		for ( var i in $scope.connections) {
			if ($scope.connections[i].username == username) {
				return i;
			}
		}
		return -1;
	};

	$scope.addConnection = function() {
		Swal.fire({
			title: 'Add Connection',
			allowOutsideClick: false,
			allowEscapeKey: false,
			allowEnterKey: false,
			onBeforeOpen: () => {
				Swal.showLoading()
			}
		});
		$http({
			method: 'POST',
			url: '/fetchallusers',
		})
		.then(function(res){
			data = res.data;
			$scope.userList = [];
			var connectionsList = $scope.connections.map(function(elem, i) {
				return elem.userID;
			})
			for(var i in data) {
				if($scope.loggedInUser.username != data[i].username && !connectionsList.includes(data[i].userID)) {
					$scope.userList.push(data[i]);
				}
			}
			Swal.close();
			angular.element('#addConnectionModal').modal('show');
		}, (err)=>{
			console.error(err);
		})
	};

	$scope.createConnection = function(userID, username, name) {
		$http({
			method: 'POST',
			url: '/addconnection',
			data: {userID: $scope.loggedInUser.userID, connectionUserID: userID}
		})
		.then((res)=>{
			if(res.data) {
				Swal.fire('Connection added successfully!', '', 'success');
				$scope.connections.push({
					username : username,
					name : name,
					lastMessage : '',
					lastUpdated : '',
					chatMessages : []
				})
				$scope.userList = $scope.userList.filter(function(elem) {
					return elem.userID != userID;
				});
				if ($scope.connections.length == 1) {
					var index = $scope.getUserIndex(username);
					$scope.selectedUser = $scope.connections[index];
				}
			}
		}, (err)=>{
			console.error(err);
		})
	};

	$scope.sendMessage = function(msg) {
		var index = $scope.getUserIndex($scope.selectedUser.username);
		var data = {
			'senderUserID' : $scope.loggedInUser.userID,
			'senderUserName' : $scope.username,
			'senderLang' : $scope.loggedInUser.language,
			'recieverUserID' : $scope.selectedUser.userID,
			'recieverUserName' : $scope.selectedUser.username,
			'recieverLang' : $scope.selectedUser.language,
			'userMessage' : msg,
		}
		$scope.websocket.send("/app/message", {}, JSON.stringify(data));
		$('.send-text').val('');
	}

	$scope.showKeyboard = function() {
		var win = open($scope.vKeyboard[$scope.lang], 'Virtual Keyboard');
	}

	$scope.changeLanguage = function(lang) {
		if ($scope.lang != lang) {
			$http({
				method: 'POST',
				url: '/updateuserlanguage',
				data: {userID: $scope.loggedInUser.userID, preferredLanguage: lang}
			})
			.then((res)=>{
				$scope.lang = lang;
				$scope.loggedInUser.lang = lang;
				setCookie('language', lang);
				Swal.fire('Language changed successfully', '', 'success');
			}, (err)=>{
				console.error(err);
			})
		}
	}

	$scope.logout = function() {
		var cookieList = ['username', 'userID', 'language', 'firstName', 'lastName', 'contactNo'];
		for(var i in cookieList) {
			deleteCookie(cookieList[i]);
		}
		location.href = '/login';
	}

}]);