$(document).ready(function() 
{
	// Validation rules
	$("#sky-form").validate(
	{					
		// Rules for form validation
		rules:
		{
			companyname:
			{
				required: true,
				rangelength:[2,20]
			},
			companyaddress:
			{
				required: true,
				rangelength:[5,40]
			}
		},
										
		// Messages for form validation
		messages:
		{
			companyname:
			{
				required: 'Please enter taxi car company name'
			},
			companyaddress:
			{
				required: 'Please enter taxi car company address'
			}
		},
					
		// AJAX form submission					
		submitHandler: function(form)
		{
			$.ajax(
			{
				url: '/TaxiToGo/TaxiToGoController/company-add-action',
				type: 'POST',
				data: 
				{
					// Getting the parameters from the form
					companyname : $('#companyname').val(),
					companyaddress : $('#companyaddress').val()
				},
				
				dataType: 'text',
				
				// Getting the result from the server
				success: function(resultFromServer) 
				{
					// If the company was added successfully
					if (resultFromServer === 'added') 
					{
						$('#result').text('Company was successfully added').show().fadeOut(4000);
						
						// The moment AJAX will receive an answer from the server, form will be reseted
						window.setTimeout(function() 
								{
									$("#sky-form")[0].reset();
								}, 4000)
					}
					else // If the company wasn't added
					{
						$('#result').text('There was a problem to add the company').show().fadeOut(4000);
						
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