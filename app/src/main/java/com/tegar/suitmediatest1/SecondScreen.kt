package com.tegar.suitmediatest1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tegar.suitmediatest1.databinding.ActivitySecondScreenBinding
import com.tegar.suitmediatest1.databinding.ActivityThirdScreenBinding

class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.btnChooseUser.setOnClickListener {
            nextToThirdScreen()
        }
    }

    private fun nextToThirdScreen() {
        val thirdScreenActivity = Intent(this@SecondScreen,ThirdScreen::class.java)
        startActivity(thirdScreenActivity)
    }

}