package com.example.quiz

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.quiz.question
import android.annotation.SuppressLint
import android.os.Bundle
import com.example.quiz.R
import androidx.annotation.RequiresApi
import android.os.Build
import android.content.res.ColorStateList
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.view.View
import com.example.quiz.Score
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import java.util.ArrayList

class Questions() : AppCompatActivity(), View.OnClickListener {
    private  var questions: TextView? = null
    private var ques_num: TextView? = null
    private lateinit var option1: Button
    private lateinit var option2: Button
    private lateinit var option3: Button
    private lateinit var option4: Button
    private var questionList: MutableList<question>? = null
    private var quesNum = 0
    private var score_num = 0
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)
        ques_num = findViewById(R.id.ques_num)
        questions = findViewById(R.id.question)
        option1 = findViewById(R.id.Option1)
        option2 = findViewById(R.id.Option2)
        option3 = findViewById(R.id.Option3)
        option4 = findViewById(R.id.Option4)
        option1.setOnClickListener(this)
        option2.setOnClickListener(this)
        option3.setOnClickListener(this)
        option4.setOnClickListener(this)
        questionsList
        score_num = 0
    }

    private val questionsList: Unit
        private get() {
            questionList = ArrayList()
            (questionList as ArrayList<question>).add(
                question(
                    "What planet is closet to the sun?",
                    "Venus",
                    "Mars",
                    "Mercury",
                    "Earth",
                    3
                )
            )
            (questionList as ArrayList<question>).add(
                question(
                    "What is national flower of India?",
                    "Rose",
                    "Lotus",
                    "Sunflower",
                    "Marigold",
                    2
                )
            )
            (questionList as ArrayList<question>).add(
                question(
                    "What is the SI unit of length",
                    "m",
                    "m/s",
                    "cm",
                    "seconds",
                    1
                )
            )
            (questionList as ArrayList<question>).add(
                question(
                    "How often a leap year occurs?",
                    "8 years",
                    "1 years",
                    "2 years",
                    "4 years",
                    4
                )
            )
            (questionList as ArrayList<question>).add(
                question(
                    "What is the largest continent on earth",
                    "South America",
                    "Europe",
                    "Africa",
                    "Asia",
                    4
                )
            )
            setQuestion()
        }

    private fun setQuestion() {
        questions!!.text = questionList!!.get(0).question
        option1!!.text = questionList!!.get(0).optionA
        option2!!.text = questionList!!.get(0).optionB
        option3!!.text = questionList!!.get(0).optionC
        option4!!.text = questionList!!.get(0).optionD
        ques_num!!.text = "Question " + 1.toString()
        quesNum = 0
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v: View) {
        var selectedOption = 0
        when (v.id) {
            R.id.Option1 -> selectedOption = 1
            R.id.Option2 -> selectedOption = 2
            R.id.Option3 -> selectedOption = 3
            R.id.Option4 -> selectedOption = 4
            else -> {}
        }
        checkAnswer(selectedOption, v)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun checkAnswer(selectedOption: Int, view: View) {
        if (selectedOption == questionList!![quesNum].correctAns) {
            //Right Answer
            (view as Button).backgroundTintList = ColorStateList.valueOf(Color.GREEN)
            score_num++
        } else {
            //Wrong Answer
            (view as Button).backgroundTintList = ColorStateList.valueOf(Color.RED)
            when (questionList!![quesNum].correctAns) {
                1 -> option1!!.backgroundTintList = ColorStateList.valueOf(
                    Color.GREEN
                )
                2 -> option2!!.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                3 -> option3!!.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                4 -> option4!!.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
            }
        }
        val handler = Handler()
        handler.postDelayed(Runnable { changeQuestion() }, 2000)
    }

    private fun changeQuestion() {
        if (quesNum < questionList!!.size - 1) {
            quesNum++
            playAnim(questions, 0, 0)
            playAnim(option1, 0, 1)
            playAnim(option2, 0, 2)
            playAnim(option3, 0, 3)
            playAnim(option4, 0, 4)
            ques_num!!.text = "Question " + (quesNum + 1).toString()
        } else {
            // Go to Score Activity
            val intent = Intent(this@Questions, Score::class.java)
            intent.putExtra("SCORE", score_num.toString() + "/" + questionList!!.size.toString())
            startActivity(intent)
            finish()
        }
    }

    private fun playAnim(view: View?, value: Int, viewNum: Int) {
        view!!.animate().alpha(value.toFloat()).scaleX(value.toFloat()).scaleY(value.toFloat())
            .setDuration(500)
            .setStartDelay(100).setInterpolator(DecelerateInterpolator())
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                override fun onAnimationEnd(animation: Animator) {
                    if (value == 0) {
                        when (viewNum) {
                            0 -> (view as TextView?)!!.text = questionList!!.get(quesNum).question
                            1 -> (view as Button?)!!.text = questionList!!.get(quesNum).optionA
                            2 -> (view as Button?)!!.text = questionList!!.get(quesNum).optionB
                            3 -> (view as Button?)!!.text = questionList!!.get(quesNum).optionC
                            4 -> (view as Button?)!!.text = questionList!!.get(quesNum).optionD
                        }
                        if (viewNum != 0) (view as Button?)!!.backgroundTintList =
                            ColorStateList.valueOf(
                                Color.parseColor("#5C2CBD")
                            )
                        playAnim(view, 1, viewNum)
                    }
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}