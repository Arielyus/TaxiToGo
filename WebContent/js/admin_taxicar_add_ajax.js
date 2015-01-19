$(document).ready(function() {
	
	// Custom validation rule for allowing letters only in a text input
	$.validator.addMethod("alpha", function(value, element) 
	{
		return this.optional(element) || /^[a-z," "]+$/i.test(value);
	}, "Please enter a valid letter");    
	
	// Custom validation rule for time format
	$.validator.addMethod("time24", function(value, element) 
	{
	    if (!/^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/.test(value)) return false;
	    var parts = value.split(':');
	    if (parts[0] > 23 || parts[1] > 59 || parts[2] > 59) return false;
	    return true;
	}, "Invalid time format");
	
	// Custom validation rule for exact length
	jQuery.validator.addMethod("exactlength", function(value, element, param) 
	{
		 return this.optional(element) || value.length == param;
	}, jQuery.format("Please enter exactly 7 numbers"));
	
	// Mask rules
	$(function()
	{
		$("#plate").mask('*******', {placeholder:'X'});
		$("#latitude").mask('**.*******', {placeholder:'X'});
		$("#longitude").mask('**.*******', {placeholder:'X'});
		$("#workinghours").mask('**:**', {placeholder:'X'});
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
			},
			classification:
			{
				required: true
			},
			drivername:
			{
				required: true,
				alpha: { alpha: true },
				rangelength:[5,20]
			},
			companyname:
			{
				required: true,
				alpha: { alpha: true },
				rangelength:[2,20]
			},
			quality:
			{
				required: true
			},
			latitude:
			{
				required: true,
				number:true,
				range:[-90,90]
			},
			longitude:
			{
				required: true,
				number:true,
				range:[-180,180]
			},
			workinghours:
			{
				required: true,
				time24: true
			}
		},
										
		// Messages for form validation
		messages:
		{
			plate:
			{
				required: 'Please enter taxi car plate number'
			},
			classification:
			{
				required: 'Please select car classification'
			},
			drivername:
			{
				required: 'Please enter taxi driver full name'
			},
			companyname:
			{
				required: 'Please enter taxi car company name'
			},
			quality:
			{
				required: 'Please rate taxi driver'
			},
			latitude:
			{
				required: 'Please enter taxi car latitude coordinate'
			},
			longitude:
			{
				required: 'Please enter taxi car longitude coordinate'
			},
			workinghours:
			{
				required: 'Please enter taxi car end shift'
			}
		},
					
		// AJAX form submission						
		submitHandler: function(form)
		{
			$.ajax(
			{
				url: '/TaxiToGo/TaxiToGoController/taxicar-add-action',
				type: 'POST',
				data: 
				{
					// Getting the parameters from the form
					plate : $('#plate').val(),
					classification : $("#classification option:selected").val(),
					drivername: $('#drivername').val(),
					companyname : $('#companyname').val(),
					rating: $("input[name='quality']:checked").val(),
					latitude: $('#latitude').val(),
					longitude: $('#longitude').val(),
					workinghours: $('#workinghours').val(),
				},
		
				dataType: 'text',
				
				// Getting the result from the server
				success: function(resultFromServer) 
				{
					// If the taxi car was added successfully
					if (resultFromServer === 'added') 
					{
						$('#result').text('Taxi car was successfully added').show().fadeOut(4000);
						
						// The moment AJAX will receive an answer from the server, form will be reseted
						window.setTimeout(function() 
								{
									$("#sky-form")[0].reset();
								}, 4000)
						
					}
					else // If the taxi car wasn't added
					{
						$('#result').text('There was a problem to add the taxi car').show().fadeOut(4000);
						
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