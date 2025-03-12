package br.com.app.cadeavan

import android.app.Application
import com.google.firebase.FirebaseApp

class CadeAVanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}