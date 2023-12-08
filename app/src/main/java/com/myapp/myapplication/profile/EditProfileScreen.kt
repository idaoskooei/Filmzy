package com.myapp.myapplication.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.ActionButton
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.CircularImageView
import com.myapp.myapplication.composables.IconButton
import com.myapp.myapplication.composables.IconButtonWithText

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddPicScreen(viewModel: EditProfileViewModel) {

    val uiState by rememberUpdatedState(newValue = viewModel.uiState.collectAsState())
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BackgroundImage(id = R.drawable.untitled_design__13_)
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularImageView(bmp = uiState.value.showImage, size = 200.dp) {}
            CameraAndGalleryButtons(
                onGalleryClick = { viewModel.openGallery() },
                onCameraClick = { viewModel.openCamera() },
                cameraPermissionState = cameraPermissionState,
            )
            ActionButton(text = "SAVE", onClick = {
                uiState.value.showImage?.let { bitmap ->
                    viewModel.onSaveButtonClicked(bitmap)
                }
            })
        }
    }
}

@Composable
@OptIn(ExperimentalPermissionsApi::class)
private fun CameraAndGalleryButtons(
    cameraPermissionState: PermissionState,
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButtonWithText(icon = Icons.Filled.CameraAlt, text = "OPEN CAMERA") {
            if (cameraPermissionState.status.isGranted) {
                onCameraClick()
            } else {
                cameraPermissionState.launchPermissionRequest()
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        IconButtonWithText(icon = Icons.Filled.PhotoLibrary, text = "OPEN GALLERY") {
            onGalleryClick()
        }
    }
}