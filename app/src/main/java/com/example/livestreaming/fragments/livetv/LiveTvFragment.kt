package com.example.livestreaming.fragments.livetv


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.livestreaming.AgoraMainActivity
import com.example.livestreaming.AgoraVideoActivity
import com.example.livestreaming.BaseFragment
import com.example.livestreaming.CommonMethods
import com.example.livestreaming.R
import com.example.livestreaming.fullVideo.FullVideoActivity
import io.agora.rtc2.Constants
import java.util.ArrayList


class LiveTvFragment : BaseFragment(R.layout.fragment_live_tv) {

    var channelProfile = 0
    var cameraGranted = false
    var permissionsStr = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    var permissionsCount = 0
    lateinit var permissionsList: ArrayList<String>

    override fun initFragment(view: View) {

        val videoViewTrending = view.findViewById<ImageView>(R.id.imageTrending)
        videoViewTrending.setOnClickListener {
            val intentImg = Intent(context, FullVideoActivity::class.java)
            intentImg.putExtra("VideoLink", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            startActivity(intentImg)
        }


        permissionsList = ArrayList()
        permissionsList.addAll(permissionsStr)
        askForPermissions(permissionsList)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val host = view.findViewById<RadioButton>(R.id.host)
        val audience = view.findViewById<RadioButton>(R.id.audience)
        val channelName = view.findViewById<EditText>(R.id.channelName)
        val submit = view.findViewById<TextView>(R.id.submit)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
           // val radioChecked: RadioButton = view.findViewById(checkedId)
            val selectdId = radioGroup.checkedRadioButtonId
            val radioChecked: RadioButton = view.findViewById(selectdId)
            Log.d("RadioGroup", radioChecked.text.toString())
          //  Toast.makeText(requireContext(), " On checked change :" + " ${radio.text}", Toast.LENGTH_SHORT).show()

          //  val checked = (view as RadioButton).isChecked
         //   when (view.id) {
            //    Log.d("RadioGroup", view.id.toString())
                /*R.id.host -> if (radioChecked.isChecked){

                //  Toast.makeText(requireContext(), " On checked change11 :" + " ${radioChecked.text}", Toast.LENGTH_SHORT).show()
                }*/
                if(radioChecked.text == "Host") {
                    channelProfile = Constants.CLIENT_ROLE_BROADCASTER
                }else{
                    channelProfile = Constants.CLIENT_ROLE_AUDIENCE
                }
              /* R.id.audience -> if (radioChecked.isChecked){
                   Toast.makeText(requireContext(), " On checked change22 :" + " ${radioChecked.text}", Toast.LENGTH_SHORT).show()
                   // channelProfile = Constants.CLIENT_ROLE_AUDIENCE
                }*/
            //}
        }

        submit.setOnClickListener{
            // if (cameraGranted){
            var id: Int = radioGroup.checkedRadioButtonId
            if (id!=-1){
                val channelName = channelName.text.toString()
                val intent = Intent(requireContext(), AgoraVideoActivity::class.java)
                intent.putExtra(AgoraMainActivity.channelMessage, channelName)
                intent.putExtra(AgoraMainActivity.profileMessage, channelProfile)
                Log.d("DataCheck11", channelName)
                Log.d("DataCheck22", channelProfile.toString())
                startActivity(intent)
                // val radio:RadioButton = findViewById(id)
                //  Toast.makeText(applicationContext,"On button click :" + " ${radio.text}", Toast.LENGTH_SHORT).show()
            }else{
                // If no radio button checked in this radio group
                Toast.makeText(requireContext(),"Please Select one Item", Toast.LENGTH_SHORT).show()
                //   }
                //}else{
                //CommonMethods.showMessage(this, getString(R.string.permission_required))
            }

        }
        val MY_PERMISSIONS_REQUEST_CAMERA = 0
        // Here, this is the current activity
        /*   if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, MY_PERMISSIONS_REQUEST_CAMERA);

        }*/


    }

    companion object {
        const val channelMessage = "com.agora.samtan.agorabroadcast.CHANNEL"
        const val profileMessage = "com.agora.samtan.agorabroadcast.PROFILE"
    }

    //-- Permission
    private var permissionsLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
        val list = ArrayList(result.values)
        permissionsList = ArrayList()
        permissionsCount = 0
        for (i in list.indices) {
            if (shouldShowRequestPermissionRationale(permissionsStr[i])) {
                permissionsList.add(permissionsStr[i])
            } else if (!hasPermission(requireContext(), permissionsStr[i])) {
                if (permissionsStr[i] === Manifest.permission.CAMERA) {
                    CommonMethods.showMessage(requireContext(), "Please allow Camera permission!")
                }
                if (permissionsStr[i] === Manifest.permission.RECORD_AUDIO) {
                    //   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    CommonMethods.showMessage(requireContext(), "Please allow MicroPhone permission!")
                    //   }
                }
                permissionsCount++
            }
        }
        if (permissionsList.size > 0) {
            askForPermissions(permissionsList)
        }
    }

    private fun hasPermission(context: Context, permissionStr: String): Boolean {

        return ContextCompat.checkSelfPermission(context, permissionStr) == PackageManager.PERMISSION_GRANTED
    }
    private fun askForPermissions(permissionsList: ArrayList<String>) {
        //val newPermissionStr = arrayOfNulls<String>(permissionsList.size)
        val newPermissionStr = Array(permissionsList.size) { "" }
        for (i in newPermissionStr.indices) {
            newPermissionStr[i] = permissionsList[i]
        }
        if (newPermissionStr.isNotEmpty()) {
            permissionsLauncher.launch(newPermissionStr)
        }
    }

}