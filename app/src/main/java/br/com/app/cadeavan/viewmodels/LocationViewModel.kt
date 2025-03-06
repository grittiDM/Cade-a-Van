package br.com.app.cadeavan.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.app.cadeavan.repositories.LocationClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationClient: LocationClient
) : ViewModel() {

    // Estado da localização do motorista
    private val _driverLocation = locationClient.getDriverLocation("driverId123")
    val driverLocation: StateFlow<Pair<Double, Double>?> = _driverLocation

    // Envia a localização do motorista
    fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            locationClient.sendDriverLocation(driverId, latitude, longitude)
        }
    }
}