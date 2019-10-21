$("#submit").on(
		'click',
		function(e) {
			e.preventDefault();
			$('#form-error').slideUp();
			var email = $("#email").val();
			var username = $("#username").val();
			var password = $("#password").val();
			var repassword = $("#repassword").val();
			var language = $("#language").val();
			var pattern = /^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$/

			if (email == '' || username == '' || password == ''
					|| repassword == '' || language == '') {
				$('#error-msg').text(
						'Please fill all the fields before submitting!');
			} else if (!pattern.test(email)) {
				$('#error-msg').text(
						'Please enter a valid Email ID!');
				$('#form-error').slideDown();
			} else if (password != repassword) {
				$('#error-msg').text(
						'Entered passwords do no match!');
				$('#form-error').slideDown();
			}
		});

$("#email").on('blur', function () {
	if ($(this).is(':valid')) {
		$('#email-group').removeClass('has-danger').addClass('has-success');
	} else {
		$('#email-group').removeClass('has-success').addClass('has-danger');
	}
});

$("#password").on('blur', ()=>{
	if ($("#repassword").val() != '') {
		validatePasswords();
	}
});

$("#repassword").on('blur', validatePasswords);

function validatePasswords() {
	if($('#password').val() === $('#repassword').val()) {
		$('.passwords').removeClass('has-danger').addClass('has-success');
	} else {
		$('.passwords').removeClass('has-success').addClass('has-danger');
	}
}