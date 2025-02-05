package br.com.app.cadeavan.repositories

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class LocationRepository {

    private val database = FirebaseDatabase.getInstance()

    // Envia a localização do motorista
    suspend fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double) {
        val ref = database.getReference("drivers/$driverId")
        val locationData = mapOf(
            "latitude" to latitude,
            "longitude" to longitude,
            "timestamp" to System.currentTimeMillis()
        )
        ref.setValue(locationData).await()
    }

    // Obtém a localização do motorista em tempo real
    fun listenToDriverLocation(driverId: String): Flow<Pair<Double, Double>> = callbackFlow {
        val ref = database.getReference("drivers/$driverId")

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val latitude = snapshot.child("latitude").getValue(Double::class.java) ?: 0.0
                val longitude = snapshot.child("longitude").getValue(Double::class.java) ?: 0.0
                trySend(Pair(latitude, longitude)).isSuccess // Emite a localização
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        ref.addValueEventListener(listener)

        awaitClose {
            ref.removeEventListener(listener)
        }
    }
}