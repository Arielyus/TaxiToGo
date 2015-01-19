  	var map;
    var geocoder;
    
    /* Finding user's current latitude and longitude coordinates. 
     * It's been used to display the user, five of the most closest 
     * taxi cars to his current location */
    if (navigator.geolocation) 
    {
    	navigator.geolocation.getCurrentPosition(function(position) 
    	{  
    	   latitude = position.coords.latitude;
    	   document.forms[0].latitude.value = latitude;
    	   longitude = position.coords.longitude;
    	   document.forms[0].longitude.value = longitude;
    	 });  
    } 
    	
    else 
    {
    	alert("I'm sorry, but geolocation services are not supported by your browser");
    }  
    
    // First initialization of the Google map
    function InitializeMap() 
    { 
    	// Finding current latitude and longitude coordinates for centering the map purpose
    	navigator.geolocation.getCurrentPosition(function(position) 
    	{
    		latitude = position.coords.latitude;
    		document.forms[0].latitude.value = latitude;
    		longitude = position.coords.longitude;
    		document.forms[0].longitude.value = longitude;
    	    
    		// Creating a Google map
    		var geolockpoint = new google.maps.LatLng(latitude, longitude);
    		
    		// Centering Google map on current latitude and longitude coordinates   
    	    map.setCenter(geolockpoint);
    	}); 
    	
    	// Google map options (zoom, map type, etc.)
        var myOptions =
        {
            zoom: 19,
            mapTypeId: google.maps.MapTypeId.ROADMAP,

        };
        map = new google.maps.Map(document.getElementById("map"), myOptions);
    }

    // Initializing Google map for searching purpose
    function InitializeMapForSearch() 
    {   	
    	// Google map options (zoom, map type, etc.)
        var myOptions =
        {
            zoom: 18,
            mapTypeId: google.maps.MapTypeId.ROADMAP,

        };
        map = new google.maps.Map(document.getElementById("map"), myOptions);
    }
    
    // Searching on Google maps by typing the location or latitude and longitude coordinates
    function FindLocaiton() {
        geocoder = new google.maps.Geocoder();
        InitializeMapForSearch();

        var address = document.getElementById("addressinput").value;
        geocoder.geocode({ 'address': address }, function (results, status) 
        {
            if (status == google.maps.GeocoderStatus.OK) 
            {
                map.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker(
                {
                    map: map,
                    position: results[0].geometry.location
                });
            }
            else 
            {
                alert("Geocode was not successful for the following reason: " + status);
            }
        });
    }

    // Start searching user's current location on a click button
    function findbutton_onclick() 
    {
        FindLocaiton();
    }

    // Initialize the Google Map the moment the page is loaded
    window.onload = InitializeMap;