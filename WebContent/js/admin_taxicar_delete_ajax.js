$(document).ready(function() 
{
	// Custom validation rule for exact length	
	jQuery.validator.addMethod("exactlength", function(value, element, param) 
	{
		 return this.optional(element) || value.length == param;
	}, jQuery.format("Please enter exactly 7 numbers"));
	
	// Retrieving the taxi car information from the server
	$('select#taxiplate').change(function() 
	{
		$.ajax(
		{
			url : '/TaxiToGo/TaxiToGoController/taxicar-information',
			type : 'POST',
			data : 
			{
				// Getting the parameter from the form
				taxiplate : $("#taxiplate option:selected").val()
			},
			
			dataType: 'json',
			
			// Getting the result from the server
			success: function(resultFromServer) 
			{
				$('#drivername').val(resultFromServer.driverName);
				$('#classification').val(resultFromServer.taxiClassification);
				$('#companyname').val(resultFromServer.taxiCompanyName);
				$("input[name=quality][value=" + resultFromServer.taxiDriverRating+"]").
					attr('checked', true);
				$('#latitude').val(resultFromServer.latitude);
				$('#longitude').val(resultFromServer.longitude);
				$('#workinghours').val(resultFromServer.workingHours);
			}
		});
	});
	
	// Validation rules
	$("#sky-form").validate(
	{					
		// Rules for form validation
		rules:
		{
			plate:
			{
				required: true,
				number:true,
				exactlength: 7
			}
		},
										
		// Messages for form validation
		messages:
		{
			plate:
			{
				required: 'Please enter taxi car plate number'
			},
		},
					
		// Ajax form submition					
		submitHandler: function(form)
		{
			$.ajax(
			{
				url: '/TaxiToGo/TaxiToGoController/taxicar-delete-action',
				type: 'POST',
				data: 
				{
					// Getting the parameter from the form
					plate : $("#taxiplate option:selected").val()
				},
				
				dataType: 'text',
				
				// Getting the result from the server
				success: function(resultFromServer) 
				{
					// If the taxi car was deleted successfully
					if (resultFromServer === 'deleted') 
					{
						$('#result').text('Taxi car was successfully deleted').show().fadeOut(4000);
						
						// The moment AJAX will receive an answer from the server, form will be reseted
						window.setTimeout(function() 
								{
									$("#sky-form")[0].reset();
								}, 4000)
					}
					else // If the taxi car wasn't deleted
					{
						$('#result').text('The taxi car doesn\'t exist').show().fadeOut(4000);
						
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