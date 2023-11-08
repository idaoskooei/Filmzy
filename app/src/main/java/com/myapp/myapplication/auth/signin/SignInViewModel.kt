package com.myapp.myapplication.auth.signin


import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.myapp.myapplication.auth.FILL_IN_BOTH_FIELDS
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.auth.model.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navController: NavController
) : ViewModel() {

    private val viewModelState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = viewModelState.stateIn(
        scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = UiState()
    )

    data class UiState(
        val isInProgress: Boolean = false,
        val errorMessage: String? = null
    )

    fun onSignInButtonClicked(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            viewModelState.value = UiState(errorMessage = FILL_IN_BOTH_FIELDS)
            return
        }
        if (!isEmailValid(email)) {
            viewModelState.update { currentViewState ->
                currentViewState.copy(
                    errorMessage = INVALID_EMAIL_ADDRESS_FORMAT_ERROR_MESSAGE
                )
            }
            return
        }

        if (!isPassValid(password)) {
            viewModelState.update { currentViewState ->
                currentViewState.copy(
                    errorMessage = INVALID_PASS_FORMAT_ERROR_MESSAGE
                )
            }
            return
        }

        viewModelState.value = UiState(isInProgress = true, errorMessage = null)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                authRepository.signInUser(email, password) { responseState ->
                    when (responseState) {
                        is ResponseState.Success -> {
                            navController.navigate("movies_page")
                        }

                        is ResponseState.Failure -> {
                            viewModelState.update { currentViewState ->
                                currentViewState.copy(errorMessage = SIGN_IN_FAILED)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                viewModelState.value = UiState(errorMessage = "An error occurred")
            }
        }
    }

    private fun isPassValid(pass: String): Boolean {
        return !TextUtils.isEmpty(pass)
    }

    private fun isEmailValid(email: String): Boolean {
        return !(TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    companion object {
        private const val INVALID_EMAIL_ADDRESS_FORMAT_ERROR_MESSAGE =
            "PLEASE ENTER A VALID EMAIL ADDRESS"
        private const val INVALID_PASS_FORMAT_ERROR_MESSAGE = "PLEASE ENTER A VALID PASSWORD"
        private const val SIGN_IN_FAILED = "SIGN IN FAILED"
    }
}