package com.example.livestreaming.fragments.livetv



import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livestreaming.BaseFragment
import com.example.livestreaming.CommonMethods
import com.example.livestreaming.R
import com.example.livestreaming.fullVideo.FullVideoActivity
import com.example.livestreaming.liveBroadcast.CreateLiveBroadcastActivity


class LiveTvFragment : BaseFragment(R.layout.fragment_live_tv) {

    private lateinit var videoList: RecyclerView
    private lateinit var broadcastNewVideo: ImageView
  //  lateinit var liveTvAdapter: LiveTvAdapter

    override fun initFragment(view: View) {
        videoList = view.findViewById(R.id.liveVideoList)
        broadcastNewVideo = view.findViewById(R.id.broadcastNewVideo)

        val videoViewTrending = view.findViewById<ImageView>(R.id.imageTrending)
        videoViewTrending.setOnClickListener {
            val intentImg = Intent(context, FullVideoActivity::class.java)
            intentImg.putExtra("VideoLink", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            startActivity(intentImg)
        }
        broadcastNewVideo.setOnClickListener {
            CommonMethods.intentActivity(requireContext(), CreateLiveBroadcastActivity())
        }
        //--  Live Video  --//
        videoList.layoutManager = GridLayoutManager(context, 2)
        videoList.setHasFixedSize(true)
        var adapter = LiveTvAdapter(generateData(), requireContext())
        videoList?.itemAnimator =  DefaultItemAnimator()
        videoList?.adapter = adapter
      //  adapter.notifyDataSetChanged()
    }

    private fun generateData(): ArrayList<UserImage> {
        var result = ArrayList<UserImage>()

        //for (i in 0..1) {
            result.add(UserImage("Movies Live", R.drawable.movie_live, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))
            result.add(UserImage("Sports Live", R.drawable.live_match, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"))
            result.add(UserImage("News Live", R.drawable.news_live, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"))
            result.add(UserImage("Comedy Live", R.drawable.comedy_live, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"))
          //  result.add(UserImage("LiveVideo5", R.drawable.live_match, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"))
          //  result.add(UserImage("LiveVideo6", R.drawable.channel_2, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"))
          //  result.add(UserImage("LiveVideo7", R.drawable.channel_3, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"))
          //  result.add(UserImage("LiveVideo8", R.drawable.channel_4, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"))
      //  }

        return result
    }

}