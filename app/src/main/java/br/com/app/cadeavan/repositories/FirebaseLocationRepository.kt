package br.com.app.cadeavan.repositories

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseLocationRepository : LocationRepository {

    private val database = FirebaseDatabase.getInstance()

    override suspend fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double) {
        val ref = database.getReference("drivers/$driverId")
        val locationData = mapOf(
            "latitude" to latitude,
            "longitude" to longitude,
            "accuracy" to location.accuracy,
            "timestamp" to System.currentTimeMillis()
        )
        ref.setValue(locationData).await()
    }

    override fun getDriverLocation(driverId: String) {
        // Implementar lógica para obter a localização do motorista
    }
}