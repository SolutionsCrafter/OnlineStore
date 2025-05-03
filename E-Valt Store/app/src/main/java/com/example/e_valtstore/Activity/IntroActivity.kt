package com.example.e_valtstore.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.e_valtstore.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}