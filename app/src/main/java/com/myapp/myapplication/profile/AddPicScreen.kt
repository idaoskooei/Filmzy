package com.myapp.myapplication.profile

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.myapp.myapplication.R
import com.myapp.myapplication.composables.ActionButton
import com.myapp.myapplication.composables.BackgroundImage
import com.myapp.myapplication.composables.ProfileImage


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AddPicScreen(
    viewModel: AddPicViewModel
) {
    val state = viewModel.uiState.collectAsState()

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    val galleryPermissionState =
        rememberPermissionState(Manifest.permission.READ_EXTERNAL_STORAGE)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BackgroundImage(id = R.drawable.untitled_design__13_)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(100.dp))
            ProfileImage(bmp = state.value.showImage)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    if (cameraPermissionState.status.isGranted) {
                        viewModel.onCameraClicked()
                    } else {
                        cameraPermissionState.launchPermissionRequest()
                    }
                }) {
                    Text(text = "OPEN CAMERA")
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Button(onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        viewModel.onGalleryClicked()
                    } else {
                        galleryPermissionState.launchPermissionRequest()
                    }
                }) {
                    Text(text = "OPEN GALLERY")
                }
            }

            ActionButton(
                text = "OK",
                onClick = { state.value.showImage?.let { viewModel.onSaveButtonClicked(it) } },

                )
        }
    }
}
