package com.myapp.myapplication.profile

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.myapp.myapplication.FilmzyViewModel
import com.myapp.myapplication.navigation.Destinations
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AddPicViewModel(private val navController: NavController) : FilmzyViewModel() {


    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UiState()
    )

    data class UiState(
        var errorMessage: String? = null,
        var showImage: Bitmap? = null,
    )


    fun onCameraClicked() {
        // navigate to camera
    }

    fun onGalleryClicked() {
        //navigate to gallery
    }


    fun onSaveButtonClicked(bitmap: Bitmap) {
        // save image and go back to profile screen
        navController.navigate(Destinations.PROFILE_ROUTE)
    }

    companion object {
        fun provideFactory(
            navController: NavController
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AddPicViewModel(navController) as T
            }
        }
    }
}