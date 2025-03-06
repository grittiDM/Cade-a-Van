package br.com.app.cadeavan.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseLocationRepository @Inject constructor() : LocationClient {

    private val database = FirebaseDatabase.getInstance()

    override suspend fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double) {
        val ref = database.getReference("drivers/$driverId")
        val locationData = mapOf(
            "latitude" to latitude,
            "longitude" to longitude,
            "timestamp" to System.currentTimeMillis()
        )
        ref.setValue(locationData).await()
    }

    override fun getDriverLocation(driverId: String): StateFlow<Pair<Double, Double>?> {
        val ref = database.getReference("drivers/$driverId")
        val _driverLocation = MutableStateFlow<Pair<Double, Double>?>(null)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val latitude = snapshot.child("latitude").getValue(Double::class.java) ?: 0.0
                val longitude = snapshot.child("longitude").getValue(Double::class.java) ?: 0.0
                _driverLocation.value = Pair(latitude, longitude)
            }

            override fun onCancelled(error: DatabaseError) {
                // Trate o erro aqui (opcional)
            }
        })

        return _driverLocation
    }
}