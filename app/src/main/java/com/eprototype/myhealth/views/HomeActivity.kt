package com.eprototype.myhealth.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eprototype.myhealth.R
import com.eprototype.myhealth.databinding.ActivityHomeBinding
import com.eprototype.myhealth.extensions.Extensions.toast
import com.eprototype.myhealth.utils.FirebaseUtils.firebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mDatabase: FirebaseDatabase
    private lateinit var mReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mDatabase = FirebaseDatabase.getInstance("https://fir-auth-84aa0-default-rtdb.asia-southeast1.firebasedatabase.app/")
        mReference = mDatabase.getReference()
        mReference = mDatabase.getReference().child("login-history").child("name")
        mReference.setValue(firebaseAuth.currentUser?.uid)

        binding.btnSignOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, SignInActivity::class.java))
            toast("signed out")
            finish()
        }
    }
}