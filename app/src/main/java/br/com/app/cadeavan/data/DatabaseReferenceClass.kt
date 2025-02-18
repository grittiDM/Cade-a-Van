package br.com.app.cadeavan.data

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DatabaseReferenceClass {
    private var database: DatabaseReference = Firebase.database.reference
}