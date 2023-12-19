package com.example.livestreaming.fragments.livetv


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.livestreaming.CommonMethods
import com.example.livestreaming.R
import com.example.livestreaming.VideoDetailsActivity
import com.example.livestreaming.subscription.SubscriptionActivity


class LiveTvAdapter(private var items: ArrayList<UserImage>, private val context: Context): RecyclerView.Adapter<LiveTvAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var userDto = items[position]
        holder?.txtName?.text = userDto.name
        holder?.posterImage?.background = ContextCompat.getDrawable(context, userDto.imageTv)
        // val requestOptions = RequestOptions().override(100, 100)
      /*  Glide.with(context)
            .load(userDto.comment)
            .centerCrop()
            .placeholder(R.color.transBlack)
            // .thumbnail(Glide.with(context).load("video_url"))
            .into(holder.posterImage!!)*/

        holder.itemLayout?.setOnClickListener {
            if (CommonMethods.getSharedPreference(context, "Subscribed") == "SubscribedPlan"){
                val intentImg = Intent(context, VideoDetailsActivity::class.java)
                intentImg.putExtra("VideoLink", userDto.videoUrl)
                intentImg.putExtra("VideoName", userDto.name)
                context.startActivity(intentImg)
            }else{
                checkLoginDialog(userDto.videoUrl)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_live_videos, parent, false)

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

    private fun checkLoginDialog(videoLink: String?){
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_subscription, null)
        val mAlertDialog = AlertDialog.Builder(context).setView(mDialogView).show()
        val okData = mAlertDialog.findViewById<TextView>(R.id.okData)
        val cancelText = mAlertDialog.findViewById<TextView>(R.id.cancelText)
        okData?.setOnClickListener {
            mAlertDialog.dismiss()
            val intentImg = Intent(context, SubscriptionActivity::class.java)
            context.startActivity(intentImg)
        }
        cancelText?.setOnClickListener {
            mAlertDialog.dismiss()
        }
        mAlertDialog.setCancelable(false)
    }
}