package com.myapp.myapplication.signup


import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.myapp.myapplication.model.AuthRepository
import com.myapp.myapplication.model.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.util.regex.Pattern
import javax.inject.Inject


class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navController: NavController
) : ViewModel() {


    private val viewModelState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = viewModelState.stateIn(
        scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = UiState()
    )

    data class UiState(
        val isInProgress: Boolean = false,
        var errorMessage: String? = null,
    )

    fun onSignUpButtonClicked(email: String, pass: String, confirmPass: String) {
        viewModelState.update { currentViewState ->
            currentViewState.copy(
                isInProgress = true, errorMessage = null
            )
        }
        if (!isEmailValid(email)) {
            viewModelState.update { currentViewState ->
                currentViewState.copy(
                    errorMessage = INVALID_EMAIL_ADDRESS_FORMAT, isInProgress = false
                )
            }
            return
        }

        if (!isPassValid(pass)) {
            viewModelState.update { currentViewState ->
                currentViewState.copy(
                    errorMessage = INVALID_PASS_FORMAT, isInProgress = false
                )
            }
            return
        }

        if (!isConfirmedPassValid(pass, confirmPass)) {
            viewModelState.update { currentViewState ->
                currentViewState.copy(
                    errorMessage = INVALID_CONFIRMED_PASS, isInProgress = false
                )
            }
            return
        }
        authRepository.signUpUser(email, pass) { state ->
            when (state) {
                is ResponseState.Failure -> {
                    viewModelState.update { currentViewState ->
                        currentViewState.copy(errorMessage = SIGN_UP_FAILED)
                    }
                }

                is ResponseState.Success -> {
                    viewModelState.update { currentViewState ->
                        currentViewState.copy(
                            isInProgress = true
                        )
                    }
                    navController.navigate("movies_page")
                }
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return !(TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    private fun isPassValid(pass: String): Boolean {
        val matcher = Pattern.compile(PASSWORD_REGEX).matcher(pass)
        return matcher.matches()
    }

    private fun isConfirmedPassValid(pass: String, confirmedPass: String): Boolean {
        val matcher = Pattern.compile(PASSWORD_REGEX).matcher(confirmedPass)
        return (pass == confirmedPass && matcher.matches())
    }

    companion object {
        private const val INVALID_EMAIL_ADDRESS_FORMAT = "PLEASE ENTER A VALID EMAIL ADDRESS"
        private const val INVALID_PASS_FORMAT = "PLEASE ENTER A VALID PASSWORD"
        private const val INVALID_CONFIRMED_PASS = "PASSWORDS DON'T MATCH"
        private const val PASSWORD_REGEX =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        private const val SIGN_UP_FAILED = "SIGN UP FAILED"
        private const val CAN_NOT_REACH_THE_SERVER = "CAN NOT REACH THE SERVER"
        private const val EMAIL_ALREADY_IN_USE = "EMAIL ALREADY IS USE"
    }
}
