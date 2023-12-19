package com.example.livestreaming

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livestreaming.fullVideo.FullVideoActivity

class VideoListActivity : AppCompatActivity() {

    lateinit var videoList: RecyclerView
    lateinit var numberDateAdapter: VideoListAdapter
    val animals: ArrayList<String> = ArrayList()
    //val videoURL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)
        videoList = findViewById(R.id.videoList)

        val videoViewTrending = findViewById<ImageView>(R.id.imageTrending)
        videoViewTrending.setOnClickListener {
            val intentImg = Intent(this, FullVideoActivity::class.java)
            intentImg.putExtra("VideoLink", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            startActivity(intentImg)
        }
        videoList.layoutManager = GridLayoutManager(this, 2)
        videoList.setHasFixedSize(true)
        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        // Access the RecyclerView Adapter and load the data into it


        var adapter = VideoListAdapter(generateData(), this)
       // val layoutManager = LinearLayoutManager(applicationContext)
       // recyclerView?.layoutManager = layoutManager
        videoList?.itemAnimator =  DefaultItemAnimator()

        videoList?.adapter = adapter
       // videoList.adapter = VideoListAdapter(animals, this)
        adapter.notifyDataSetChanged()
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