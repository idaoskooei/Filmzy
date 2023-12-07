package com.myapp.myapplication.profile

import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.ActionButton
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.CircularImageView

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddPicScreen(viewModel: AddPicViewModel) {

    val uiState by rememberUpdatedState(newValue = viewModel.uiState.collectAsState())
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val galleryPermissionState =
        rememberPermissionState(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        BackgroundImage(id = R.drawable.untitled_design__13_)
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularImageView(bmp = uiState.value.showImage, size = 200.dp) {}
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ActionButton(onClick = {
                    if (cameraPermissionState.status.isGranted) {
                        viewModel.openCamera()
                    } else {
                        cameraPermissionState.launchPermissionRequest()
                    }
                }, text = "OPEN CAMERA")
                Spacer(modifier = Modifier.padding(10.dp))
                ActionButton(onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        viewModel.openGallery()
                    } else {
                        galleryPermissionState.launchPermissionRequest()
                    }
                }, text = "OPEN GALLERY")
            }

            ActionButton(text = "SAVE", onClick = {
                uiState.value.showImage?.let { bitmap ->
                    viewModel.onSaveButtonClicked(bitmap)
                }
            })
        }
    }
}