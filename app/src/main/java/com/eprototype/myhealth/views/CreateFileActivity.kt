package com.eprototype.myhealth.views

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.eprototype.myhealth.databinding.ActivityCreateFileBinding
import com.eprototype.myhealth.extensions.Extensions.toast
import java.io.*


class CreateFileActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCreateFileBinding

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
            val file:String = fileName.text.toString()
            val data:String = fileData.text.toString()
            val fileOutputStream:FileOutputStream
            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray())
            } catch (e: FileNotFoundException){
                e.printStackTrace()
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }catch (e: IOException){
                e.printStackTrace()
            }catch (e: Exception){
                e.printStackTrace()
            }
            toast("data save")
            fileName.text.clear()
            fileData.text.clear()
        })

        btnView.setOnClickListener(View.OnClickListener {
            val filename = fileName.text.toString()
            if(filename!=null && filename.trim()!=""){
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null
                while (run {
                        text = bufferedReader.readLine()
                        text
                    } != null) {
                    stringBuilder.append(text)
                }
                //Displaying data on EditText
                fileData.setText(stringBuilder.toString()).toString()
            }else{
                toast("file name cannot be blank")
            }
        })

    }
}