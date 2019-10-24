$('#login-btn').on('click', function(e) {
	var username = $("#username").val();
	var password = $("#password").val();
	if (username == '' || password == '') {
		$('#error-msg').text(
				'Please fill all the fields before submitting!');
		$('#form-error').slideDown();
	} else {
		$.ajax({
			url: '/login',
			type: 'POST',
			data: {'username': username, 'password': password},
			success: (msg) => {
				if(msg == 'Success') {
					Swal.fire(
					  'Login Successful!',
					  '',
					  'success'
					).then((result) => {
					  if (result.value) {
					  	location.href = '/chat';
					  }
					})
				} else {
					Swal.fire({
					  type: 'error',
					  title: 'Invalid Username or Password',
					  text: 'Please check the credentials entered and try again!',
					  footer: 'Having trouble signing in? Click here to <a href>Reset Password</a>'
					})
				}
			},
			error: (e) => {
				$('#error-msg').text(
						"Something went wrong. Couldn't login");
				$('#form-error').slideDown();
			}
		});
	}
});