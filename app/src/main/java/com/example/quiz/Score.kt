package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import com.example.quiz.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.quiz.Questions

class Score : AppCompatActivity() {
    private lateinit var score: TextView
    private lateinit var restart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        score = findViewById(R.id.score_num)
        restart = findViewById(R.id.restart)
        val score_str = intent.getStringExtra("SCORE")
        score.setText(score_str)
        restart.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@Score, Questions::class.java)
            startActivity(intent)
            finish()
        })
    }
}