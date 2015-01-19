$(document).ready(function() 
{
	
	// Validation for login form
	$("#sky-form").validate(
	{
		// Rules for form validation
		rules : 
		{
			email : 
			{
				required : true,
				email : true
			},
			password : 
			{
				required : true,
				minlength : 3,
				maxlength : 20
			}
		},

		// Messages for form validation
		messages : 
		{
			email : 
			{
				required : 'Please enter your email address',
				email : 'Please enter a VALID email address'
			},
			password : 
			{
				required : 'Please enter your password'
			}
		},
		
		// AJAX form submission					
		submitHandler: function(form)
		{
			$.ajax(
			{
				url: '/TaxiToGo/TaxiToGoController/login-action',
				type: 'POST',
				data: 
				{
					// Getting the parameters from the form
					user : $("#inputEmail").val(),
					password: $("#inputPassword").val(),
					keepLog: $('input:checkbox:checked').val()
				},
				
				dataType: 'text',
				
				// Getting the result from the server
				success: function(resultFromServer) 
				{
					// If the user is logged out
					if (resultFromServer === 'logout') 
					{
						$('#result').text('Incorrect Email/Password Combination').show().fadeOut(4000);
					}
					else // If the user is logged in
					{
						$('#result').text('You\'ve been logged in successfully').show().fadeOut(3000);
						window.setTimeout(function() 
						{
							window.location.replace("admin_index.jsp");
						}, 3000)
					}
				}
			});
		},

		errorPlacement : function(error, element) 
		{
			error.insertAfter(element.parent());
		}
	});
});