package br.com.app.cadeavan.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {

    private val repository = LocationRepository()

    // Estado da localização do motorista
    private val _driverLocation = MutableStateFlow<Pair<Double, Double>?>(null)
    val driverLocation: StateFlow<Pair<Double, Double>?> = _driverLocation

    // Estado de carregamento
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Estado de erro
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Envia a localização do motorista
    fun sendDriverLocation(driverId: String, latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true // Indica que a operação está em andamento
                repository.sendDriverLocation(driverId, latitude, longitude) // Envia a localização
                _isLoading.value = false // Indica que a operação foi concluída
            } catch (e: Exception) {
                _error.value = "Erro ao enviar localização: ${e.message}" // Armazena o erro
                _isLoading.value = false // Indica que a operação foi concluída (com erro)
            }
        }
    }

    // Obtém a localização do motorista em tempo real
    fun listenToDriverLocation(driverId: String) {
        viewModelScope.launch {
            repository.listenToDriverLocation(driverId)
                .onStart { _isLoading.value = true }
                .catch { e ->
                    _error.value = "Erro ao obter localização: ${e.message}"
                    _isLoading.value = false
                }
                .collect { location ->
                    _isLoading.value = false
                    _driverLocation.value = location
                }
        }
    }
}