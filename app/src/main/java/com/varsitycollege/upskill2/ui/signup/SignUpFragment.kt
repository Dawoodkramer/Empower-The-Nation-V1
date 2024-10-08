package com.varsitycollege.upskill2.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.varsitycollege.upskill2.databinding.FragmentSignupBinding
import com.varsitycollege.upskill2.ui.home.HomeFragment

class SignUpFragment : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Set up click listener for the sign-up button
        binding.signUpBtn.setOnClickListener {
            val email = binding.emailTxtInp.text.toString()
            val password = binding.passwordTxtInp.text.toString()

            // Check if email and password fields are not empty
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Show the progress bar
                binding.progressBar.visibility = android.view.View.VISIBLE

                // Start sign-up process
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        // Hide the progress bar
                        binding.progressBar.visibility = android.view.View.GONE

                        if (task.isSuccessful) {
                            // Sign-up success, navigate to the home page
                            val intent = Intent(this, HomeFragment::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // If sign-up fails, display a message to the user
                            Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Notify the user to fill in all fields
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
