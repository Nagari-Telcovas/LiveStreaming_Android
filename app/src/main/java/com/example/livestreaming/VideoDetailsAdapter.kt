package com.example.livestreaming



import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class VideoDetailsAdapter(private var items: ArrayList<UserDto>, private val context: Context): RecyclerView.Adapter<VideoDetailsAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var userDto = items[position]
        holder?.txtName?.text = userDto.name
        // val requestOptions = RequestOptions().override(100, 100)
        Glide.with(context)
            .load(userDto.comment)
            .centerCrop()
            .placeholder(R.color.transBlack)
            // .thumbnail(Glide.with(context).load("video_url"))
            .into(holder.posterImage!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_media, parent, false)

        return ViewHolder(itemView)
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtName: TextView? = null
        var posterImage: ImageView? = null
        var itemLayout: ConstraintLayout? = null

        init {
            this.txtName = row?.findViewById(R.id.videoName)
            this.posterImage = row?.findViewById(R.id.poster_image)
            this.itemLayout = row?.findViewById(R.id.itemLayout)
        }
    }
}