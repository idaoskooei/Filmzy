package com.myapp.myapplication.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.myapp.myapplication.FilmzyViewModel
import com.myapp.myapplication.auth.model.AuthRepository
import com.myapp.myapplication.navigation.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ProfileViewModel(
    private val navController: NavController,
    private val authRepository: AuthRepository,
) : FilmzyViewModel() {

    private val _uiState = MutableStateFlow(UiState(null))
    val uiState: StateFlow<UiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UiState()
    )

    data class UiState(
        var showProfileImage: Uri? = null,
    )

    fun onProfileButtonClicked() {
        navController.navigate(Destinations.HOME_ROUTE)
    }

    fun onSignOutButtonClicked() {
        authRepository.signOut()
        navController.navigate(Destinations.INTRO_ROUTE)
    }

    fun onEditProfileClicked() {
        navController.navigate(Destinations.ADD_PIC_SCREEN)
    }

    fun setSelectedImage(selectedImageUri: Uri?) {
        _uiState.update { currentViewState -> currentViewState.copy(showProfileImage = selectedImageUri) }
    }

    companion object {
        fun provideFactory(
            repository: AuthRepository,
            navController: NavController,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProfileViewModel(
                    navController = navController,
                    authRepository = repository,
                ) as T
            }
        }
    }
}