package com.norm.mycameraappmvvm

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.norm.mycameraappmvvm.presentation.CameraScreen
import com.norm.mycameraappmvvm.ui.theme.MyCameraAppMVVMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arePermissionGranted()) {
            ActivityCompat.requestPermissions(
                this, CAMERA_PERMISSION, 100
            )
        }

        setContent {
            MyCameraAppMVVMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CameraScreen(this)
                }
            }
        }
    }

    fun arePermissionGranted(): Boolean {
        return CAMERA_PERMISSION.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission,
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        val CAMERA_PERMISSION = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO,
        )
    }
}