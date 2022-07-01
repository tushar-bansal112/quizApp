package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quiz.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.quiz.Questions

class MainActivity : AppCompatActivity() {
    private lateinit var Start: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Start = findViewById(R.id.btn_start)
        Start.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, Questions::class.java)
            startActivity(intent)
            finish()
        })
    }
}