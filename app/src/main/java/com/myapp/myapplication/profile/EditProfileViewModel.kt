package com.myapp.myapplication.profile

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.myapp.myapplication.FilmzyViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.io.File
import java.io.IOException

class EditProfileViewModel(
    private val navController: NavController,
    @SuppressLint("StaticFieldLeak") private val context: Context
) : FilmzyViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = UiState()
    )
    private lateinit var tempImageUri: Uri

    private val cameraLauncher: ActivityResultLauncher<Uri> =
        (context as ComponentActivity).activityResultRegistry.register(
            "camera",
            ActivityResultContracts.TakePicture()
        ) { success ->
            if (success) {
                _uiState.value = uiState.value.copy(
                    showImage = tempImageUri
                )
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessage = FAILED_LOADING_CAMERA
                    )
                }
            }
        }

    private val galleryLauncher: ActivityResultLauncher<String> =
        (context as ComponentActivity).activityResultRegistry.register(
            "gallery",
            ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            if (uri != null) {
                _uiState.value = uiState.value.copy(
                    showImage = uri
                )
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        errorMessage = FAILED_LOADING_GALLERY
                    )
                }
            }
        }

    fun openCamera() {
        val photoUri = createImageUri()
        tempImageUri = photoUri
        cameraLauncher.launch(photoUri)
    }

    fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    private fun createImageUri(): Uri {
        val imageFileName = "JPEG_" + System.currentTimeMillis() + "_"
        val storageDir = context.getExternalFilesDir(android.os.Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        return FileProvider.getUriForFile(
            context,
            "com.myapp.myapplication.provider",
            image
        )
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    data class UiState(
        var errorMessage: String? = null,
        var showImage: Uri? = null,
    )

    fun onSaveButtonClicked() {

        navController.previousBackStackEntry
            ?.savedStateHandle
            ?.set("selectedImage", _uiState.value.showImage)
// TODO: save profile image in shared preferences
        navController.popBackStack()
    }

    fun onBackButtonClicked() {
        navController.popBackStack()
    }

    companion object {

        const val FAILED_LOADING_CAMERA = "we were not able to open Camera for you.try again!"
        const val FAILED_LOADING_GALLERY = "we were not able to open Gallery for you.try again!"
        fun provideFactory(
            navController: NavController,
            context: Context
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return EditProfileViewModel(navController, context) as T
            }
        }
    }
}