package com.caron.instagram.common.firebase

import com.caron.instagram.common.AuthManager
import com.caron.instagram.common.toUnit
import com.caron.instagram.data.firebase.common.auth
import com.google.android.gms.tasks.Task

class FirebaseAuthManager : AuthManager {
    override fun signOut() {
        auth.signOut()
    }

    override fun signIn(email: String, password: String): Task<Unit> =
        auth.signInWithEmailAndPassword(email, password).toUnit()
}