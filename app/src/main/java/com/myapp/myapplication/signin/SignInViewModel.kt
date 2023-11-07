package com.myapp.myapplication.signin

import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    // Add properties for data and state if needed

    fun signIn(email: String, password: String): Boolean {
        // Implement your sign-in logic here
        // Return true if sign-in is successful, false otherwise
        return !email.isBlank() && !password.isBlank()
    }
}
fun validateSignIn(email: String, password: String): Boolean {
    // You can add your sign-in validation logic here
    // For example, check if the email and password are valid
    return !email.isBlank() && !password.isBlank()
}
