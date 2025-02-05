package br.com.app.cadeavan.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.app.cadeavan.ui.components.MapView

@Composable
fun DriverScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("A Van esta aqui!")
        Spacer(modifier = Modifier.height(16.dp))
        MapView(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("main") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voltar")
        }
    }
}