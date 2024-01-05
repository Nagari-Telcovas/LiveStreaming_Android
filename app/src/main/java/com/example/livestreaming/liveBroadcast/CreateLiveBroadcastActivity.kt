package com.example.livestreaming.liveBroadcast


import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.livestreaming.AgoraMainActivity
import com.example.livestreaming.AgoraVideoActivity
import com.example.livestreaming.BaseActivity
import com.example.livestreaming.CommonMethods
import com.example.livestreaming.R
import com.example.livestreaming.databinding.ActivityCreateLiveBroadcastBinding
import io.agora.rtc2.Constants
import java.util.ArrayList

class CreateLiveBroadcastActivity : BaseActivity<ActivityCreateLiveBroadcastBinding>(ActivityCreateLiveBroadcastBinding::inflate, R.string.liveBroadcast) {

    var channelProfile = Constants.CLIENT_ROLE_AUDIENCE
    var cameraGranted = false
    var permissionsStr = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    var permissionsCount = 0
    lateinit var permissionsList: ArrayList<String>

    override fun initialization(bindingScreen: ActivityCreateLiveBroadcastBinding) {

        permissionsList = ArrayList()
        permissionsList.addAll(permissionsStr)
        askForPermissions(permissionsList)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val submit = findViewById<TextView>(R.id.submit)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            // val radioChecked: RadioButton = view.findViewById(checkedId)
            val selectdId = radioGroup.checkedRadioButtonId
            val radioChecked: RadioButton = findViewById(selectdId)
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
            //  if (id!=-1){
            val channelName = bindingScreen.channelName.text.toString()
            val intent = Intent(this, AgoraVideoActivity::class.java)
            intent.putExtra(AgoraMainActivity.channelMessage, channelName)
            intent.putExtra(AgoraMainActivity.profileMessage, channelProfile)
            startActivity(intent)
            // val radio:RadioButton = findViewById(id)
            //  Toast.makeText(applicationContext,"On button click :" + " ${radio.text}", Toast.LENGTH_SHORT).show()
            //   }else{
            // If no radio button checked in this radio group
            //     Toast.makeText(requireContext(),"Please Select one Item", Toast.LENGTH_SHORT).show()
            //   }
            //}else{
            //CommonMethods.showMessage(this, getString(R.string.permission_required))
            //   }

        }
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
            } else if (!hasPermission(this, permissionsStr[i])) {
                if (permissionsStr[i] === Manifest.permission.CAMERA) {
                    CommonMethods.showMessage(this, "Please allow Camera permission!")
                }
                if (permissionsStr[i] === Manifest.permission.RECORD_AUDIO) {
                    //   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    CommonMethods.showMessage(this, "Please allow MicroPhone permission!")
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