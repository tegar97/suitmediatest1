package com.tegar.suitmediatest1

import android.R
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.tegar.suitmediatest1.databinding.ActivitySecondScreenBinding


class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ThirdScreen.REQUEST_CODE && result.data != null) {
            val selectedValue =
                result.data?.getStringExtra(ThirdScreen.FIRST_NAME)
            binding.selectUserText.text = "Selected : $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val toolbar = binding.toolbar

        binding.backButton.setOnClickListener {
            onBackButtonClick()
        }



        binding.btnChooseUser.setOnClickListener {
            nextToThirdScreen()
        }
    }

    private fun nextToThirdScreen() {
        val thirdScreenActivity = Intent(this@SecondScreen,ThirdScreen::class.java)
        resultLauncher.launch(thirdScreenActivity)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            val firstName = data?.getStringExtra("firstName")
//
//            binding.selectUserText.text = firstName
//            // Lakukan sesuatu dengan data yang diterima
//            Log.d("YourCallingActivity", "Received firstName: $firstName")
//        }
//    }

    private fun onBackButtonClick() {
        finish()
    }
    companion  object {
        const val REQUEST_CODE = 123
    }

}