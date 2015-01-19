$(document).ready(function() 
{
	// Custom validation rule for allowing letters only in a text input
	$.validator.addMethod("alpha", function(value, element) 
	{
		return this.optional(element) || /^[a-z," "]+$/i.test(value);
	}, "Please enter a valid letter");    

	// Retrieving an information of a specific taxi car company from the database
	$('select#companyname').change(function() 
	{
		$.ajax(
		{
			url : '/TaxiToGo/TaxiToGoController/company-information',
			type : 'POST',
			data : {
				companyName : $("#companyname option:selected").val()
			},
			
			dataType: 'json',
			
			success: function(resultFromServer) 
			{
				$('#companyaddress').val(resultFromServer.companyAddress);
			}
		});
	});
	
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
				url: '/TaxiToGo/TaxiToGoController/company-update-action',
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
					// If the company was updated successfully
					if (resultFromServer === 'updated') 
					{
						$('#result').text('Company was successfully updated').show().fadeOut(4000);
						
						// The moment AJAX will receive an answer from the server, form will be reseted
						window.setTimeout(function() 
								{
									$("#sky-form")[0].reset();
								}, 4000)
					}
					else // If the company wasn't updated
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