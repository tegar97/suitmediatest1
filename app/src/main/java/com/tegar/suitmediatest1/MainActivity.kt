package com.tegar.suitmediatest1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.tegar.suitmediatest1.databinding.ActivityMainBinding
import com.tegar.suitmediatest1.utils.Logic
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private var editTextName: TextView? = null
    private var editTextSentence: TextView? = null
    private var btnCheck: Button? = null
    private var btnNext: Button? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()


    }

    private fun setupViews() {
        binding.apply {
            editTextName = inputName
            editTextSentence = inputSentence
        }
    }

    private fun setupListeners() {
        binding.btnCheck.setOnClickListener { onCheckClick() }
        binding.btnNext.setOnClickListener { nextToSecondScreen() }
    }

    private fun nextToSecondScreen() {
        val name = editTextName?.text?.toString() ?: ""

        if (name.isNullOrEmpty()) {
            Toast.makeText(this, R.string.please_fill_name, Toast.LENGTH_SHORT).show()
        } else {
            val secondScreenIntent = Intent(this@MainActivity, SecondScreen::class.java)
            secondScreenIntent.putExtra("NAME_EXTRA", name)
            startActivity(secondScreenIntent)
        }

    }

    private fun onCheckClick() {
        val sentence = editTextSentence?.text.toString().lowercase(Locale.ROOT).replace("\\s".toRegex(), "")
        val isPalindrome = Logic.isPalindrome(sentence)

        val message = when {
            sentence.isEmpty() -> getString(R.string.sentence_empty_message)
            isPalindrome -> getString(R.string.palindrome)
            else -> getString(R.string.not_palindrome)
        }
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.result_title))
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }


}