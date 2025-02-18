package br.com.app.cadeavan.repositories

interface LocationRepository {
    suspend fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double)
    fun getDriverLocation(driverId: String)
}