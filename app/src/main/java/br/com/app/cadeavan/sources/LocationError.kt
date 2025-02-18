package br.com.app.cadeavan.viewmodels

sealed class LocationError {
    data class NetworkError(val message: String) : LocationError()
    data class PermissionError(val message: String) : LocationError()
    data class FirebaseError(val message: String) : LocationError()
    data class UnknownError(val message: String) : LocationError()
}