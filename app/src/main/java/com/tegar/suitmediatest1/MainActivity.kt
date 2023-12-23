package com.tegar.suitmediatest1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private  var editTextName: TextView? = null
    private  var editTextSentence: TextView? = null
    private  var btnCheck: Button? = null
    private  var btnNext: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.input_name)
        editTextSentence = findViewById(R.id.input_sentence)
        btnCheck = findViewById(R.id.btn_check)
        btnNext = findViewById(R.id.btn_next)

        btnCheck?.setOnClickListener{
            onCheckClick()
        }

        btnNext?.setOnClickListener {
            nextToSecondScreen()
        }


    }

    private fun nextToSecondScreen() {
        val secondScreenIntent = Intent(this@MainActivity,SecondScreen::class.java)
        startActivity(secondScreenIntent)
    }
    private fun onCheckClick() {
        val sentence = editTextSentence?.text.toString().lowercase(Locale.ROOT)
            .replace("\\s".toRegex(), "")
        val isPalindrome = checkPalindrome(sentence)

        val message = if (isPalindrome) "Palindrome" else "Bukan Palindrome"

        AlertDialog.Builder(this)
            .setTitle("Hasil")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun checkPalindrome(s: String): Boolean {
        var start = 0
        var end = s.length - 1

        while (start < end) {
            if (s[start] != s[end]) {
                return false
            }
            start++
            end--
        }

        return true
    }
}