package com.tegar.suitmediatest1


import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
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



            val dynamicString = if (selectedValue == "extra_first_name") {
                getString(R.string.selected_dynamic_value, "User Name")
            } else {
                getString(R.string.selected_dynamic_value, selectedValue)
            }
            binding.selectUserText.text = dynamicString
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)


        setContentView(binding.root)
        val userNameText = binding.userNameText
        val name = intent.getStringExtra("NAME_EXTRA")
        userNameText.text = name
        binding.backButton.setOnClickListener {
            onBackButtonClick()
        }



        binding.btnChooseUser.setOnClickListener {
            nextToThirdScreen()
        }
    }
    private fun onBackButtonClick(){
        finish()
    }

    private fun nextToThirdScreen() {
        val thirdScreenActivity = Intent(this@SecondScreen,ThirdScreen::class.java)
        resultLauncher.launch(thirdScreenActivity)
    }




}