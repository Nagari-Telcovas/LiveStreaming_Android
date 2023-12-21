package com.example.livestreaming

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.agora.rtc2.Constants
import java.util.ArrayList

class AgoraMainActivity : AppCompatActivity() {
    var channelProfile = 0
    var cameraGranted = false
    var permissionsStr = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    var permissionsCount = 0
    lateinit var permissionsList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agora_main)
        //checkPermissionCamera()
        permissionsList = ArrayList()
        permissionsList.addAll(permissionsStr)
        askForPermissions(permissionsList)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val host = findViewById<RadioButton>(R.id.host)
        val audience = findViewById<RadioButton>(R.id.audience)
        val channelName = findViewById<EditText>(R.id.channelName)
        val submit = findViewById<Button>(R.id.submit)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            Toast.makeText(applicationContext, " On checked change :" + " ${radio.text}", Toast.LENGTH_SHORT).show()
            if(radio.text == ""){
                channelProfile = Constants.CLIENT_ROLE_BROADCASTER
            }else{

            }
        }

        submit.setOnClickListener{
           // if (cameraGranted){
                var id: Int = radioGroup.checkedRadioButtonId
                if (id!=-1){
                    val channelName = channelName.text.toString()
                    val intent = Intent(this, AgoraVideoActivity::class.java)
                    intent.putExtra(channelMessage, channelName)
                    intent.putExtra(profileMessage, channelProfile)
                    Log.d("DataCheck11", channelName)
                    Log.d("DataCheck22", channelProfile.toString())
                    startActivity(intent)
                   // val radio:RadioButton = findViewById(id)
                  //  Toast.makeText(applicationContext,"On button click :" + " ${radio.text}", Toast.LENGTH_SHORT).show()
                }else{
                    // If no radio button checked in this radio group
                    Toast.makeText(applicationContext,"Please Select one Item", Toast.LENGTH_SHORT).show()
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



/*    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked
        when (view.getId()) {
            R.id.host -> if (checked) {
                channelProfile = Constants.CLIENT_ROLE_BROADCASTER
            }

            R.id.audience -> if (checked) {
                channelProfile = Constants.CLIENT_ROLE_AUDIENCE
            }
        }
    }*/

    /*fun onSubmit(view: View?) {
       // val channel = findViewById<View>(R.id.channel) as EditText
        val channelName = channel.text.toString()
        val intent = Intent(this, AgoraVideoActivity::class.java)
        intent.putExtra(channelMessage, channelName)
        intent.putExtra(profileMessage, channelProfile)
        startActivity(intent)
    }*/

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

    private fun checkPermissionCamera() {
        when {
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
              //  optionTakingImage()
                cameraGranted = true
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) -> {
                CommonMethods.showMessage(this, getString(R.string.permission_required))
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted)
                cameraGranted = true
                //optionTakingImage()
            else
                CommonMethods.showMessage(this, getString(R.string.permission_required))
        }
}