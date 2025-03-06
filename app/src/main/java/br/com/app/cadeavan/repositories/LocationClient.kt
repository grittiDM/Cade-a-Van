package br.com.app.cadeavan.repositories

import kotlinx.coroutines.flow.StateFlow

interface LocationClient {
    // Obtém a localização do motorista
    fun getDriverLocation(driverId: String): StateFlow<Pair<Double, Double>?>

    // Envia a localização do motorista para o servidor
    suspend fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double)
}