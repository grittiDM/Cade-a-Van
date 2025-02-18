package br.com.app.cadeavan.data

data class User(
    val name: String,
    val email: String,
    val passwordHash: String
) {
    companion object {
        fun fromMap(map: Map<String, Any?>): User {
            return User(
                name = map["name"] as? String ?: "",
                email = map["email"] as? String ?: "",
                passwordHash = map["passwordHash"] as? String ?: ""
            )
        }
    }
}