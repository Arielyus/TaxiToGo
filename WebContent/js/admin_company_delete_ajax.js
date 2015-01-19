$(document).ready(function() 
{
	// Custom validation rule for allowing letters only in a text input
	$.validator.addMethod("alpha", function(value, element) 
	{
		return this.optional(element) || /^[a-z," "]+$/i.test(value);
	}, "Please enter a valid letter");   

	// Validation rules
	$("#sky-form").validate(
	{					
		// Rules for form validation
		rules:
		{
			companyname:
			{
				required: true,
				alpha: { alpha: true },
			},
		},
										
		// Messages for form validation
		messages:
		{
			companyname:
			{
				required: 'Please enter taxi car company name'
			},
		},
					
		// AJAX form submission					
		submitHandler: function(form)
		{
			$.ajax(
			{
				url: '/TaxiToGo/TaxiToGoController/company-delete-action',
				type: 'POST',
				data: 
				{
					// Getting the parameter from the form
					companyname : $('#companyname').val()
				},
				
				dataType: 'text',
				
				// Getting the result from the server
				success: function(resultFromServer) 
				{	
					// If the company was deleted successfully
					if (resultFromServer === 'deleted') 
					{
						$('#result').text('Company was successfully deleted').show().fadeOut(4000);
						
						// The moment AJAX will receive an answer from the server, form will be reseted
						window.setTimeout(function() 
								{
									$("#sky-form")[0].reset();
								}, 4000)
					}
					else // If the company wasn't deleted
					{
						$('#result').text('The company doesn\'t exist').show().fadeOut(4000);
						
						// The moment AJAX will receive an answer from the server, form will be reseted
						window.setTimeout(function() 
								{
									$("#sky-form")[0].reset();
								}, 4000)
					}
				}
			});
		},
			
		errorPlacement: function(error, element) 
		{
			error.insertAfter(element.parent());
		}	
	});	
});