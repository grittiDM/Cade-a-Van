package br.com.app.cadeavan.interfaces

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

interface LocationRepository {

    /**
     * Retrieves the driver's location once.
     *
     * @param driverId The ID of the driver.
     * @return A Pair of (latitude, longitude) or null if not found.
     */
    suspend fun getDriverLocation(driverId: String): Pair<Double, Double>?

    /**
     * Sends the driver's location to the database.
     *
     * @param driverId The ID of the driver.
     * @param latitude The latitude of the driver's location.
     * @param longitude The longitude of the driver's location.
     */
    suspend fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double)

    /**
     * Listens to the driver's location in real-time.
     *
     * @param driverId The ID of the driver.
     * @return A Flow emitting Pairs of (latitude, longitude) whenever the location changes.
     */
    fun listenToDriverLocation(driverId: String): Flow<Pair<Double, Double>>

    /**
     * Removes the listener for the driver's location.
     *
     * @param driverId The ID of the driver.
     */
    fun removeDriverLocationListener(driverId: String)

    /**
     * Updates the driver's location in the database.
     *
     * @param driverId The ID of the driver.
     * @param latitude The latitude of the driver's location.
     * @param longitude The longitude of the driver's location.
     */
    suspend fun updateDriverLocation(driverId: String, latitude: Double, longitude: Double)
}

class FirebaseLocationRepository(private val database: FirebaseDatabase) : LocationRepository {

    override suspend fun getDriverLocation(driverId: String): Pair<Double, Double>? {
        val ref = database.getReference("drivers/$driverId")
        return try {
            val dataSnapshot = ref.get().await()
            if (dataSnapshot.exists()) {
                val latitude = dataSnapshot.child("latitude").getValue(Double::class.java) ?: 0.0
                val longitude = dataSnapshot.child("longitude").getValue(Double::class.java) ?: 0.0
                Pair(latitude, longitude)
            } else {
                null
            }
        } catch (e: Exception) {
            // Handle exceptions (e.g., network issues)
            null
        }
    }

    override suspend fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double) {
        val ref = database.getReference("drivers/$driverId")
        val locationData = mapOf(
            "latitude" to latitude,
            "longitude" to longitude,
            "timestamp" to System.currentTimeMillis()
        )
        ref.setValue(locationData).await()
    }

    override fun listenToDriverLocation(driverId: String): Flow<Pair<Double, Double>> = callbackFlow {
        val ref = database.getReference("drivers/$driverId")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val latitude = snapshot.child("latitude").getValue(Double::class.java) ?: 0.0
                val longitude = snapshot.child("longitude").getValue(Double::class.java) ?: 0.0
                trySend(Pair(latitude, longitude))
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        ref.addValueEventListener(listener)

        awaitClose { ref.removeEventListener(listener) }
    }

    override fun removeDriverLocationListener(driverId: String) {
        val ref = database.getReference("drivers/$driverId")
        ref.removeValue()
    }

    override suspend fun updateDriverLocation(driverId: String, latitude: Double, longitude: Double) {
        val ref = database.getReference("drivers/$driverId")
        val locationData = mapOf(
            "latitude" to latitude,
            "longitude" to longitude,
            "timestamp" to System.currentTimeMillis()
        )
        ref.updateChildren(locationData).await()
    }
}