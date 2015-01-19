$(document).ready(function() 
{
	$('.btn-s').on('click',function(event) 
	{
		event.preventDefault();
		
	$(this).prop('disabled', true);
		
		// AJAX form submission
		$.ajax(
		{
			url: '/TaxiToGo/TaxiToGoController/taxicar-delete-favorite',
			type: 'POST',
			data: 
			{
				// Getting the parameters from the form
				plate : $(this).attr("id")
			},
			
			dataType: 'text',
			
			complete: function() 
			{
				setTimeout(function()
				{
					$('#conteudo_upload').hide();
					$('#loading').show(); 
					}, 800);
				
				setTimeout(function()
				{
					window.location.reload();
					}, 0000);
				}
			});
		});
	});