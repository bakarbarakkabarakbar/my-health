package com.eprototype.myhealth.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eprototype.myhealth.databinding.ActivityEmailVerificationBinding
import com.eprototype.myhealth.utils.FirebaseUtils.firebaseAuth
import com.google.firebase.auth.FirebaseUser

class EmailVerificationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailVerificationBinding
    lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailVerificationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (firebaseAuth.currentUser != null){
            user = firebaseAuth.currentUser!!
            user.sendEmailVerification()
        }

        binding.button3.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}