package com.myapp.myapplication.auth.signup


import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.myapp.myapplication.auth.INVALID_EMAIL_ADDRESS_FORMAT
import com.myapp.myapplication.auth.INVALID_PASS_FORMAT
import com.myapp.myapplication.auth.PASSWORDS_DO_NOT_MATCH
import com.myapp.myapplication.auth.PASSWORD_REGEX
import com.myapp.myapplication.auth.SIGN_UP_FAILED
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.auth.model.ResponseState
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
                isInProgress = true,
                errorMessage = null
            )
        }
        authRepository.signUpUser(email, pass) { state ->
            when (state) {
                is ResponseState.Failure -> {
                    if (!isEmailValid(email)) {
                        viewModelState.update { currentViewState ->
                            currentViewState.copy(
                                isInProgress = false,
                                errorMessage = INVALID_EMAIL_ADDRESS_FORMAT
                            )
                        }
                    } else if (!isPassValid(pass)) {
                        viewModelState.update { currentViewState ->
                            currentViewState.copy(
                                isInProgress = false,
                                errorMessage = INVALID_PASS_FORMAT
                            )
                        }
                    } else if (isConfirmedPassValid(pass, confirmPass) != isPassValid(pass)) {
                        viewModelState.update { currentViewState ->
                            currentViewState.copy(
                                isInProgress = false,
                                errorMessage = PASSWORDS_DO_NOT_MATCH
                            )
                        }
                    } else {
                        viewModelState.update { currentViewState ->
                            currentViewState.copy(
                                isInProgress = false,
                                errorMessage = SIGN_UP_FAILED
                            )
                        }
                    }

                }

                is ResponseState.Success -> {
                    viewModelState.update { currentViewState ->
                        currentViewState.copy(
                            isInProgress = false
                        )
                    }
                    navController.navigate("movies_page")
                }
            }
        }

        viewModelState.update { currentViewState ->
            currentViewState.copy(
                errorMessage = SIGN_UP_FAILED,
                isInProgress = false
            )
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

}
