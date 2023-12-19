package com.example.livestreaming


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VideoDetailsActivity : AppCompatActivity() {

    lateinit var similarVideoList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_details)
        val videoView = findViewById<VideoView>(R.id.ePlayerVideo)
        val share_ll = findViewById<LinearLayout>(R.id.share_ll)
        val videoTitle = findViewById<TextView>(R.id.title_text)
        videoTitle.text = intent.getStringExtra("VideoName")
       videoView.setVideoPath(intent.getStringExtra("VideoLink"))
        videoView.setZOrderOnTop(true)
        val mediaController = MediaController(this)

        mediaController.setAnchorView(videoView)

        mediaController.setMediaPlayer(videoView)

        videoView.setMediaController(mediaController)
        videoView.start()

        similarVideoList = findViewById(R.id.similarMoviesList)
        similarVideoList.layoutManager = GridLayoutManager(this, 3)
        similarVideoList.setHasFixedSize(true)
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it


        var adapter = VideoDetailsAdapter(generateData(), this)
        // val layoutManager = LinearLayoutManager(applicationContext)
        // recyclerView?.layoutManager = layoutManager
        similarVideoList?.itemAnimator =  DefaultItemAnimator()

        similarVideoList?.adapter = adapter
        adapter.notifyDataSetChanged()
        share_ll.setOnClickListener {
            var imagePathUrl = "Video Link: ${intent.getStringExtra("VideoLink")}"
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "$imagePathUrl\n, Play Latest Video.")
            startActivity(Intent.createChooser(shareIntent, getString(R.string.app_name)))
        }
    }

    private fun generateData(): ArrayList<UserDto> {
        var result = ArrayList<UserDto>()

        for (i in 0..1) {
            //var user =
            // result.add(UserDto("Bett", "Awesome work"))
            // result.add(UserDto("LiveVideo1", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"))
            //  result.add(UserDto("LiveVideo2", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"))
            result.add(UserDto("LiveVideo1", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))
            result.add(UserDto("LiveVideo2", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"))
            result.add(UserDto("LiveVideo3", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"))
            result.add(UserDto("LiveVideo4", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"))
            result.add(UserDto("LiveVideo5", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"))
            result.add(UserDto("LiveVideo6", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"))
            result.add(UserDto("LiveVideo7", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"))
            result.add(UserDto("LiveVideo8", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"))
        }

        return result
    }
}