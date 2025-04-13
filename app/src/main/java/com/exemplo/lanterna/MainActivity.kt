package com.exemplo.lanterna

import android.hardware.Camera
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var camera: Camera? = null
    private var isFlashOn = false
    private var params: Camera.Parameters? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnToggle = findViewById<Button>(R.id.btnToggle)

        camera = Camera.open()
        params = camera?.parameters

        btnToggle.setOnClickListener {
            if (isFlashOn) {
                turnOffFlash()
                btnToggle.text = "Ligar Lanterna"
            } else {
                turnOnFlash()
                btnToggle.text = "Desligar Lanterna"
            }
        }
    }

    private fun turnOnFlash() {
        if (camera != null && !isFlashOn) {
            params?.flashMode = Camera.Parameters.FLASH_MODE_TORCH
            camera?.parameters = params
            camera?.startPreview()
            isFlashOn = true
        }
    }

    private fun turnOffFlash() {
        if (camera != null && isFlashOn) {
            params?.flashMode = Camera.Parameters.FLASH_MODE_OFF
            camera?.parameters = params
            camera?.stopPreview()
            isFlashOn = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        camera?.release()
    }
}
