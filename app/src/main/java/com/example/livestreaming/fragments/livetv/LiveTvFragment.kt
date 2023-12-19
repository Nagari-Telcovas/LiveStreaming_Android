package com.example.livestreaming.fragments.livetv


import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.livestreaming.BaseFragment
import com.example.livestreaming.R
import com.example.livestreaming.UserDto
import com.example.livestreaming.fullVideo.FullVideoActivity


class LiveTvFragment : BaseFragment(R.layout.fragment_live_tv) {

    private lateinit var videoList: RecyclerView
    private lateinit var newsList: RecyclerView
    private lateinit var moviesList: RecyclerView
    private lateinit var blogsList: RecyclerView
    lateinit var liveTvAdapter: LiveTvAdapter
    lateinit var newsAdapter: LiveTvAdapter
    lateinit var movieAdapter: LiveTvAdapter
    lateinit var blogsAdapter: LiveTvAdapter

    override fun initFragment(view: View) {
        videoList = view.findViewById(R.id.liveVideoList)
        newsList = view.findViewById(R.id.newsList)
        moviesList = view.findViewById(R.id.moviesList)
        blogsList = view.findViewById(R.id.blogsList)

        val videoViewTrending = view.findViewById<ImageView>(R.id.imageTrending)
        videoViewTrending.setOnClickListener {
            val intentImg = Intent(context, FullVideoActivity::class.java)
            intentImg.putExtra("VideoLink", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            startActivity(intentImg)
        }
        //--  Live Video  --//
        videoList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        videoList.setHasFixedSize(true)
        var adapter = LiveTvAdapter(generateData(), requireContext())
        videoList?.itemAnimator =  DefaultItemAnimator()
        videoList?.adapter = adapter
        adapter.notifyDataSetChanged()

        //--  News Video  --//
        newsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        newsList.setHasFixedSize(true)
        newsAdapter = LiveTvAdapter(generateData(), requireContext())
        newsList?.itemAnimator =  DefaultItemAnimator()
        newsList?.adapter = newsAdapter
        adapter.notifyDataSetChanged()

        //--  Movies Video  --//
        moviesList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        moviesList.setHasFixedSize(true)
        movieAdapter = LiveTvAdapter(generateData(), requireContext())
        moviesList?.itemAnimator =  DefaultItemAnimator()
        moviesList?.adapter = movieAdapter
        adapter.notifyDataSetChanged()

        //--  Blogs Video  --//
        blogsList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        blogsList.setHasFixedSize(true)
        blogsAdapter = LiveTvAdapter(generateData(), requireContext())
        blogsList?.itemAnimator =  DefaultItemAnimator()
        blogsList?.adapter = blogsAdapter
        adapter.notifyDataSetChanged()
    }

    private fun generateData(): ArrayList<UserImage> {
        var result = ArrayList<UserImage>()

        for (i in 0..1) {
            result.add(UserImage("LiveVideo1", R.drawable.live_match, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"))
            result.add(UserImage("LiveVideo2", R.drawable.channel_2, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"))
            result.add(UserImage("LiveVideo3", R.drawable.channel_3, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4"))
            result.add(UserImage("LiveVideo4", R.drawable.channel_4, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4"))
            result.add(UserImage("LiveVideo5", R.drawable.live_match, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerMeltdowns.mp4"))
            result.add(UserImage("LiveVideo6", R.drawable.channel_2, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/Sintel.mp4"))
            result.add(UserImage("LiveVideo7", R.drawable.channel_3, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4"))
            result.add(UserImage("LiveVideo8", R.drawable.channel_4, "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"))
        }

        return result
    }

}