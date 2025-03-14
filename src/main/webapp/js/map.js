var map = L.map('map').setView([7.3024349690262325, 80.63555247183888], 25); //Kandy

//Load OpenStreetMap tiles
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
	attribution: '&copy; OpenStreetMap contributors'
}).addTo(map);

var pickupMarker, dropMarker, pickupCoords, dropCoords;

//Click event to select pickup and drop locations
map.on('click', function (e) {
    var selectedLatLng = e.latlng;

    if (!pickupMarker) {
        pickupMarker = L.marker(selectedLatLng, { draggable: true }).addTo(map).bindPopup('Fetching address...').openPopup();
        pickupCoords = selectedLatLng;
        reverseGeocode(pickupCoords, 'Pickup Location');
        // Add dragend event to update the address when dragged
        pickupMarker.on('dragend', function () {
            pickupCoords = pickupMarker.getLatLng();
            reverseGeocode(pickupCoords, 'Pickup Location');
        });
    } else if (!dropMarker) {
        dropMarker = L.marker(selectedLatLng, { draggable: true }).addTo(map).bindPopup('Fetching address...').openPopup();
        dropCoords = selectedLatLng;
        reverseGeocode(dropCoords, 'Drop Location');
        // Add dragend event to update the address when dragged
        dropMarker.on('dragend', function () {
            dropCoords = dropMarker.getLatLng();
            reverseGeocode(dropCoords, 'Drop Location');
        });
    }
});

// Function to reverse geocode and get the location name
function reverseGeocode(latlng, locationType) {
    var lat = latlng.lat;
    var lng = latlng.lng;
    var url = `https://nominatim.openstreetmap.org/reverse?lat=${lat}&lon=${lng}&format=json`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
        	var { building, road, suburb, city } = data.address || {};
        	var displayAddress = `${building ? building : ""} ${road ? road : ""} ${suburb  ? suburb : ""} ${city ? city : ""}`;

            // Update the popup with the location's name/address
            if (locationType === 'Pickup Location') {
                pickupMarker.setPopupContent(`Pickup Location: ${displayAddress != "" ?displayAddress: "No display address found." }`).openPopup();
                document.getElementById('pickupLocation').value = displayAddress;
                document.getElementById('pickupLocationLabel').innerText = displayAddress;
				
               
            } else if (locationType === 'Drop Location') {
                dropMarker.setPopupContent(`Drop Location: ${displayAddress}`).openPopup();
                document.getElementById('destination').value = displayAddress;
                document.getElementById('destinationLabel').innerText = displayAddress;
            }
        })
        .catch(error => {
            console.error("Error fetching location:", error);
        });
}

function resetLocations() {
    // Remove the markers from the map if they exist
    if (pickupMarker) {
        map.removeLayer(pickupMarker);
        pickupMarker = null;
    }
    if (dropMarker) {
        map.removeLayer(dropMarker);
        dropMarker = null;
    }

    // Clear the coordinates
    pickupCoords = null;
    dropCoords = null;
    map.removeLayer(routeLayer);
}

//Function to calculate distance using OpenRouteService
function calculateDistance() {
	if (!pickupCoords || !dropCoords) {
		alert('Please select both pickup and drop locations.');
		return;
	}

	var orsApiKey = '5b3ce3597851110001cf6248ee30f263434d4df09566b2866c0dc783'; // Replace with your API key
	var url =
		"https://api.openrouteservice.org/v2/directions/driving-car?api_key=" +
		orsApiKey +
		"&start=" +
		pickupCoords.lng +
		"," +
		pickupCoords.lat +
		"&end=" +
		dropCoords.lng +
		"," +
		dropCoords.lat;

	console.log("Generated URL:", url);

	fetch(url)
		.then((response) => response.json())
		.then((data) => {
			if (data.features && data.features.length > 0) {
				var route = data.features[0];
				var distanceInKm = (route.properties.summary.distance / 1000).toFixed(2);
				var durationInMinutes = (route.properties.summary.duration / 60).toFixed(2);
				document.getElementById('distanceLabel').innerText = `${distanceInKm} km`;
				document.getElementById('estimatedTimeLabel').innerText = `${durationInMinutes} min`;

				document.getElementById('pickupLat').value = pickupCoords.lat;
                document.getElementById('pickupLng').value = pickupCoords.lng;
                document.getElementById('destinationLat').value = dropCoords.lat;
                document.getElementById('destinationLng').value = dropCoords.lng;
                document.getElementById('distance').value = distanceInKm;
                document.getElementById('estimatedTime').value = durationInMinutes;
				
				// Call function to draw route on map
				drawRoute(route.geometry.coordinates);
			} else {
				console.error('No routes found.');
			}
		})
		.catch((error) => console.error('Error fetching distance:', error));
}

var routeLayer; // Store the route line

function drawRoute(coordinates) {
	if (routeLayer) {
		map.removeLayer(routeLayer); // Remove existing route if any
	}

	// Convert coordinates from [lng, lat] to [lat, lng]
	var latLngs = coordinates.map((coord) => [coord[1], coord[0]]);

	// Draw route on map
	routeLayer = L.polyline(latLngs, { color: 'blue', weight: 5 }).addTo(map);

	// Zoom the map to fit the route
	map.fitBounds(routeLayer.getBounds());
}



//$(document).ready(function () {
//    $("#bookRideForm").on("submit", function (event) {
//        event.preventDefault(); 
//        var formData = new FormData(this);
//
//        $.ajax({
//            url: "customer?action=createBooking",
//            type: "POST",
//            data: formData,
//            processData: false,
//            contentType: false, 
//            success: function(data) {
//				alert("success"); 
//            },
//            error: function(xhr, status, error) {
//				alert("failed"); 
//                console.error("AJAX request failed: " + error);
//            }
//        });
//    });
//});

