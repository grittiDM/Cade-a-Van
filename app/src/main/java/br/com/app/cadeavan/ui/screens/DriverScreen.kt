package br.com.app.cadeavan.ui.screens

import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.app.cadeavan.services.LocationService
import br.com.app.cadeavan.ui.components.MapView
import br.com.app.cadeavan.viewmodels.LocationError

@Composable
fun DriverScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: LocationViewModel = hiltViewModel()
    val driverLocation by viewModel.driverLocation.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        val intent = Intent(context, LocationService::class.java).apply {
            putExtra("driverId", "driverId123")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        } else {
            context.startService(intent)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("A Van está aqui!")
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            Text("Carregando localização...")
        } else if (error != null) {
            when (error) {
                is LocationError.NetworkError -> Text("Erro de rede: ${error.message}", color = Color.Red)
                is LocationError.PermissionError -> Text("Permissão negada: ${error.message}", color = Color.Red)
                is LocationError.FirebaseError -> Text("Erro no Firebase: ${error.message}", color = Color.Red)
                is LocationError.UnknownError -> Text("Erro desconhecido: ${error.message}", color = Color.Red)
                else -> Text("Erro inesperado", color = Color.Red)
            }
        } else {
            MapView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                driverLocation = driverLocation
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("Início") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}