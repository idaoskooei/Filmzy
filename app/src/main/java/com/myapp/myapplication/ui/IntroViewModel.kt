package com.myapp.myapplication.ui

import androidx.lifecycle.ViewModel
import com.myapp.myapplication.ui.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {

    fun onProceed() {
        navigationManager.navigateToHome()
    }
}