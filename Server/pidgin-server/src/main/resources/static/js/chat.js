var app = angular.module('OOAD', []);
app.controller('pidgin', ['$scope', '$http', function($scope, $http) {
	$scope.loading = true;
	$scope.username = getCookie('username');
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
	// $scope.connections = [
	// {
	// 	username : 'h',
	// 	name : 'Howard',
	// 	lastMessage : "Yes, I'm on it. I'll send it as soon as I am done.",
	// 	lastUpdated : '3 days ago',
	// 	chatMessages : [
	// 	{
	// 		sender : 'h',
	// 		receiver : 'm',
	// 		message : 'यह तुरंत किए जाने की आवश्यकता है',
	// 		translatedMessage : 'This needs to be done immediately',
	// 	},
	// 	{
	// 		sender : 'm',
	// 		receiver : 'h',
	// 		message : "Yes, I'm on it. I'll send it as soon as I am done.",
	// 		translatedMessage : 'हां, मैं इस पर कायम हूं। मैं जैसे ही करूंगा, भेज दूंगा।'
	// 	}]
	// },
	// {
	// 	username : 'n',
	// 	name : 'Manuel',
	// 	lastMessage : "Yes, I'm on it. I'll send it as soon as I am done.",
	// 	lastUpdated : '4 days ago',
	// 	chatMessages : [
	// 	{
	// 		sender : 'n',
	// 		receiver : 'm',
	// 		message : "Esto debe hacerse de inmediato",
	// 		translatedMessage : 'This needs to be done immediately',
	// 	},
	// 	{
	// 		sender : 'm',
	// 		receiver : 'n',
	// 		message : "Yes, I'm on it. I'll send it as soon as I am done.",
	// 		translatedMessage : 'Sí, estoy en eso. Lo enviaré tan pronto como termine.'
	// 	}]
	// },
	// {
	// 	username : 'g',
	// 	name : 'George',
	// 	lastMessage : "Yes, I'm on it. I'll send it as soon as I am done.",
	// 	lastUpdated : '5 days ago',
	// 	chatMessages : [
	// 	{
	// 		sender : 'g',
	// 		receiver : 'm',
	// 		message : "Dies muss sofort erfolgen",
	// 		translatedMessage : 'This needs to be done immediately',
	// 	},
	// 	{
	// 		sender : 'm',
	// 		receiver : 'g',
	// 		message : "Yes, I'm on it. I'll send it as soon as I am done.",
	// 		translatedMessage : 'Ja, ich bin dabei. Ich werde es senden, sobald ich fertig bin.'
	// 	}]
	// },
	// {
	// 	username : 'k',
	// 	name : 'Karen',
	// 	lastMessage : "Yes, I'm on it. I'll send it as soon as I am done.",
	// 	lastUpdated : '6 days ago',
	// 	chatMessages : [
	// 	{
	// 		sender : 'k',
	// 		receiver : 'm',
	// 		message : "ಇದನ್ನು ತಕ್ಷಣ ಮಾಡಬೇಕಾಗಿದೆ",
	// 		translatedMessage : 'This needs to be done immediately',
	// 	},
	// 	{
	// 		sender : 'm',
	// 		receiver : 'k',
	// 		message : "Yes, I'm on it. I'll send it as soon as I am done.",
	// 		translatedMessage : 'ಹೌದು, ನಾನು ಅದರ ಮೇಲೆ ಇದ್ದೇನೆ. ನಾನು ಮುಗಿದ ತಕ್ಷಣ ಅದನ್ನು ಕಳುಹಿಸುತ್ತೇನೆ.'
	// 	}]
	// }];

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

	function deleteCookie(cname) {
		document.cookie = cname + "=;expires=Thu; 01 Jan 1970";
	}

	$scope.ready = function() {
		if ($scope.username == '') {
			location.href = '/login';
		}
		$scope.connect();
		if ($scope.connections.length > 0) {
			$scope.currentUser = $scope.connections[0].username;
		}
	};

	$scope.onMessage = function(messageJson) {
		var msgJson = JSON.parse(messageJson);
		console.log(msgJson);

	}
	$scope.connect = function() {
		
		$scope.websocket = Stomp.client('ws://localhost:8080/chatroom');
		$scope.websocket.connect({
			'username': $scope.loggedInUser.username
		}, function() {
			$scope.websocket.subscribe("/topic/chatroom", function(message) {
				console.log(message);
			});
			$scope.websocket.subscribe("/user/queue/private", $scope.onMessage);
			$scope.websocket.send("/app/sendall", {}, $scope.loggedInUser.userID);
		}, function(error) {
			console.log(message);
		});

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
					$scope.currentUser = username;
				}
			}
		}, (err)=>{
			console.error(err);
		})
	};

	$scope.sendMessage = function(msg) {
		var reciever = $scope.getUserIndex($scope.currentUser);
		var data = {
			'senderUserID' : $scope.loggedInUser.userID,
			'senderUserName' : $scope.username,
			'senderLang' : $scope.loggedInUser.language,
			'receiverUserID' : reciever.userID,
			'receiverUserName' : $scope.currentUser,
			'recieverLang' : reciever.language,
			'userMessage' : msg,
		}
		$scope.websocket.send("/app/message", {}, JSON.stringify(data));
		// $.ajax({
		// 	url : '/translate',
		// 	type : 'POST',
		// 	data : {
		// 		'sender' : $scope.username,
		// 		'receiver' : $scope.currentUser,
		// 		'text' : msg
		// 	},
		// 	success : function(data) {
		// 		var data = {
		// 			sender : $scope.username,
		// 			receiver : $scope.currentUser,
		// 			message : msg,
		// 			translatedMessage : data
		// 		}
		// 		var index = $scope.getUserIndex($scope.currentUser);
		// 		$scope.connections[index].chatMessages.push(data);
		// 		$scope.connections[index].lastMessage = msg;
		// 		$scope.connections[index].lastUpdated = 'Today';
		// 		$("#chatbox").animate({
		// 			scrollTop : $('#chatbox').get(0).scrollHeight
		// 		}, 1000);
		// 		$('#send-text').val('');
		// 		$scope.$digest();
		// 	},
		// 	error : function(e) {
		// 		console.error(e)
		// 	}
		// });
	}

	$scope.showKeyboard = function() {
		var win = open('https://gate2home.com/Hindi-Keyboard', 'Virtual Keyboard');
	}

	$scope.print = function(msg) {
		console.log(msg);
	}

	$scope.logout = function() {
		var cookieList = ['username', 'userID', 'language', 'firstName', 'lastName', 'contactNo'];
		for(var i in cookieList) {
			deleteCookie(cookieList[i]);
		}
		location.href = '/login';
	}

}]);