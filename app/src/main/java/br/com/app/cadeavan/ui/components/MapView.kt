package br.com.app.cadeavan.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    driverLocation: Pair<Double, Double>? // Localização do motorista
) {
    val defaultLocation = LatLng(-23.5505, -46.6333) // Exemplo: Coordenadas de São Paulo
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 15f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        driverLocation?.let { (latitude, longitude) ->
            val location = LatLng(latitude, longitude)
            Marker(
                position = location,
                title = "Localização do Motorista"
            )
        }
    }
}