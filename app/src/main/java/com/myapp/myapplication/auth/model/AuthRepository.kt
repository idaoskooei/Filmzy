package com.myapp.myapplication.auth.model

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepository {

    private var auth: FirebaseAuth? = null

    fun signInUser(email: String, pass: String, onResponseReady: (ResponseState) -> Unit) {
        auth = Firebase.auth
        auth!!.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth!!.currentUser
                    onResponseReady(ResponseState.Success(user.toUser()))
                } else {
                    onResponseReady(ResponseState.Failure("Authentication failed"))
                }
            }
            .addOnFailureListener { error ->
                onResponseReady(ResponseState.Failure(error.toString()))
            }
    }

    fun signUpUser(email: String, pass: String, onResponseReady: (ResponseState) -> Unit) {
        auth = Firebase.auth
        auth!!.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user: FirebaseUser? = auth!!.currentUser
                    onResponseReady(ResponseState.Success(user.toUser()))
                } else {
                    onResponseReady(ResponseState.Failure("Authentication failed"))
                }
            }
            .addOnFailureListener { error ->
                onResponseReady(ResponseState.Failure(error.toString()))
            }
    }

    fun isUserSignedIn(): Boolean {
        return Firebase.auth.currentUser != null
    }

    fun signOut() {
        val auth = getAuthInstance()
        auth.signOut()
    }

    private fun getAuthInstance(): FirebaseAuth {
        if (auth == null) {
            auth = Firebase.auth
        }
        return auth!!
    }

    private fun FirebaseUser?.toUser(): User {
        return User(
            uId = this?.uid,
            displayName = this?.displayName,
            phoneNumber = this?.phoneNumber,
            photoUrl = this?.photoUrl.toString(),
            isEmailVerified = this?.isEmailVerified,
        )
    }
}