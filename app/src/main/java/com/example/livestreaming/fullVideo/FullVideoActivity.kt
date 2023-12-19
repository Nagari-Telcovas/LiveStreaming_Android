package com.example.livestreaming.fullVideo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.MediaController
import android.widget.VideoView
import com.example.livestreaming.R

class FullVideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_video)
        val videoView = findViewById<VideoView>(R.id.ePlayerItem)

        videoView.setVideoPath(intent.getStringExtra("VideoLink"))
        videoView.setZOrderOnTop(true)
        val mediaController = MediaController(this)

        mediaController.setAnchorView(videoView)

        mediaController.setMediaPlayer(videoView)

        videoView.setMediaController(mediaController)
        videoView.start()
    }
}