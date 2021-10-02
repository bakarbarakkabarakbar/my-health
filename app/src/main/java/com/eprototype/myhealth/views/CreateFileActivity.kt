package com.eprototype.myhealth.views

import android.app.Activity
import android.app.KeyguardManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyPermanentlyInvalidatedException
import android.security.keystore.KeyProperties
import android.security.keystore.UserNotAuthenticatedException
import android.util.Base64.*
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.eprototype.myhealth.databinding.ActivityCreateFileBinding
import com.eprototype.myhealth.extensions.Extensions.toast
import java.io.*
import java.lang.Byte.decode
import java.math.BigInteger
import java.security.*
import java.util.*
import javax.security.auth.x500.X500Principal


class CreateFileActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCreateFileBinding
    private val filepath = "MyFileStorage"
    private var myExternalFile: File?=null

    private val isExternalStorageReadOnly: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED_READ_ONLY == extStorageState
    }

    private val isExternalStorageAvailable: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED == extStorageState
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateFileBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fileName = binding.editFile
        val fileData = binding.editData

        val btnSave = binding.btnSave
        val btnView = binding.btnView

        btnSave.setOnClickListener(View.OnClickListener {
            myExternalFile = File(getExternalFilesDir(filepath), fileName.text.toString())
            try {
                val fileOutPutStream = FileOutputStream(myExternalFile)
                fileOutPutStream.write(fileData.text.toString().toByteArray())
                fileOutPutStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            toast("data save")
            fileName.text.clear()
            fileData.text.clear()
        })

        btnView.setOnClickListener(View.OnClickListener {
            val filename = fileName.text.toString()
            myExternalFile = File(getExternalFilesDir(filepath), filename)

            if (filename != null && filename.trim() != "") {
                val fileInputStream = FileInputStream(myExternalFile)
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String?
                while (run {
                        text = bufferedReader.readLine()
                        text
                    } != null) {
                    stringBuilder.append(text)
                }
                fileInputStream.close()
                //Displaying data on EditText
                fileData.setText(stringBuilder.toString()).toString()
            } else {
                toast("file name cannot be blank")
            }
        })
    }
}

