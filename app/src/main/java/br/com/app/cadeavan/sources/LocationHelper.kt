package br.com.app.cadeavan.sources

import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationResult

class LocationHelper {

    companion object {
        private const val TAG = "LocationHelper"
    }

    /**
     * Processes the location result received from the Fused Location Provider.
     *
     * @param locationResult The result containing a list of locations.
     * @param sendLocationToFirebase A lambda function to send the location to Firebase.
     */

    fun handleLocationResult(locationResult: LocationResult?, sendLocationToFirebase: (Location) -> Unit) {
        // Check if the location result is null or if the locations list is empty.
        if (locationResult == null || locationResult.locations.isEmpty()) {
            Log.w(TAG, "Location result is null or empty.")
            return
        }

        // Use forEach for a more idiomatic way to iterate through the locations.
        locationResult.locations.forEach { location ->
            // Log the location details.
            Log.d(TAG, "Latitude: ${location.latitude}, Longitude: ${location.longitude}, Accuracy: ${location.accuracy}, Time: ${location.time}")

            // Check if the location is valid before sending it to Firebase.
            if (isValidLocation(location)) {
                sendLocationToFirebase(location)
            } else {
                Log.w(TAG, "Invalid location received: $location")
            }
        }
    }

    /**
     * Checks if the given location is valid.
     *
     * @param location The location to check.
     * @return True if the location is valid, false otherwise.
     */

    private fun isValidLocation(location: Location): Boolean {
        // Add your validation logic here. For example:
        // Check if the accuracy is within an acceptable range.
        // Check if the time is recent.
        // Check if latitude and longitude are within valid ranges.
        return location.accuracy > 0 && location.latitude in -90.0..90.0 && location.longitude in -180.0..180.0
    }
}