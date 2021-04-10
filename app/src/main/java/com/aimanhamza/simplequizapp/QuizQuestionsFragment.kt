package com.aimanhamza.simplequizapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat


class QuizQuestionsFragment : Fragment(), View.OnClickListener {
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    // TODO (STEP 3: Create a variable for getting the name from intent.)
    // START
    private var mUserName: String? = null
    // END

    lateinit var tv_option_one: TextView
    lateinit var tv_option_two:TextView
    lateinit var tv_option_three:TextView
    lateinit var tv_option_four:TextView
    lateinit var btn_submit : Button
    lateinit var progressBar: ProgressBar
    lateinit var tv_progress:TextView
    lateinit var tv_question:TextView
    lateinit var iv_image: ImageView
    lateinit var btn_return:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_quiz_questions, container, false)
        tv_option_one = view.findViewById(R.id.tv_option_one)
        tv_option_two = view.findViewById(R.id.tv_option_two)
        tv_option_three = view.findViewById(R.id.tv_option_three)
        tv_option_four = view.findViewById(R.id.tv_option_four)
        btn_submit = view.findViewById(R.id.btn_submit)
        progressBar = view.findViewById(R.id.progressBar)
        tv_progress = view.findViewById(R.id.tv_progress)
        tv_question = view.findViewById(R.id.tv_question)
        iv_image = view.findViewById(R.id.iv_image)
        btn_return = view.findViewById(R.id.btn_return)

        btn_return.setOnClickListener {
            if (mCurrentPosition != 1){
                mCurrentPosition--

                setQuestion()
            }
        }
        // TODO (STEP 4: Get the NAME from intent and assign it the variable.)
        // START
       // mUserName = intent.getStringExtra(Constants.USER_NAME)
        // END

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.tv_option_one -> {

                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {

                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {

                selectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four -> {

                selectedOptionView(tv_option_four, 4)
            }

            R.id.btn_return -> {
                if (mCurrentPosition != 1){
                    mCurrentPosition--

                    setQuestion()
                }
            }

            R.id.btn_submit -> {

                if(mCurrentPosition < mQuestionsList!!.size){
                    mCurrentPosition++

                    setQuestion()
                }else{
                    // TODO (STEP 5: Now remove the toast message and launch the result screen which we have created and also pass the user name and score details to it.)
                    // START
                    var score = 0

                    for (question in mQuestionsList!!){
                        if(question.chosenAnswer == question.correctAnswer){
                            score++;
                        }
                    }
                    (activity as MainActivity).setCscore(score)
                    // END
                }


            }
        }
    }

    private fun setQuestion() {

        val question = mQuestionsList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "SUIVANT"
        }

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.getMax()

        tv_question.text = question.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        when(question.chosenAnswer){
            1 -> selectedOptionView(tv_option_one, 1)
            2 -> selectedOptionView(tv_option_two, 2)
            3 -> selectedOptionView(tv_option_three, 3)
            4 -> selectedOptionView(tv_option_four, 4)
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        mQuestionsList!!.get(mCurrentPosition - 1).chosenAnswer = selectedOptionNum


        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = context?.let {
            ContextCompat.getDrawable(
                it,
                R.drawable.selected_option_border_bg
            )
        }
    }


    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = this!!.context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.default_option_border_bg
                )
            }
        }
    }

}

