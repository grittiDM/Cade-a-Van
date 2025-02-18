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
        if (locationResult == null || locationResult.locations.isEmpty()) {
            Log.w(TAG, "Location result is null or empty.")
            return
        }

        locationResult.locations.forEach { location ->
            Log.d(TAG, "Latitude: ${location.latitude}, Longitude: ${location.longitude}, Accuracy: ${location.accuracy}, Time: ${location.time}")

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
        val isRecent = System.currentTimeMillis() - location.time < 60000 // 1 minute
        return location.accuracy > 0 &&
                location.latitude in -90.0..90.0 &&
                location.longitude in -180.0..180.0 &&
                isRecent
    }
}