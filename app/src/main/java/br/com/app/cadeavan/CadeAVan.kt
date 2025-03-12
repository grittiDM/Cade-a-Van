package br.com.app.cadeavan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.app.cadeavan.ui.navigation.AppNavigation
import com.google.firebase.FirebaseApp
import br.com.app.cadeavan.ui.theme.CadeAVanTheme

class CadeAVan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            CadeAVanTheme {
                AppNavigation()
            }
        }
    }
}