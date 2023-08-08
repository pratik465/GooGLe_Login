package com.example.googlelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.googlelogin.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("438431947620-ecpi41uk3dhhf4mv8g8q993k3vs49ltm.apps.googleusercontent.com")
            .requestEmail()
            .build()

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(this@MainActivity, googleSignInOptions)
        btSignIn.setOnClickListener { // Initialize sign in intent
            val intent: Intent = googleSignInClient.signInIntent
            // Start activity for result
            startActivityForResult(intent, 100)
        }

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        // Initialize firebase user
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        // Check condition
        if (firebaseUser != null) {
            // When user already sign in redirect to profile activity
            startActivity(
                Intent(
                    this@MainActivity,
                    ProfileActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )

        }
    }