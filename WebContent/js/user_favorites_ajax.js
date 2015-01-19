$(document).ready(function() 
{
	$('.btn-s').on('click',function(event) 
	{
		event.preventDefault();
	
	$(this).prop('disabled', true);
		
		// AJAX form submission
		$.ajax(
		{
			url: '/TaxiToGo/TaxiToGoController/taxicar-add-favorite',
			type: 'POST',
			data: 
			{
				// Getting the parameters from the form
				plate : $(this).attr("id")
			},
			
			dataType: 'text',
			
			// Getting the result from the server
			success: function(data) 
			{
			    if (data == "refresh") 
			    {
			      window.location.reload();
			    }
			}
		});
	});
});