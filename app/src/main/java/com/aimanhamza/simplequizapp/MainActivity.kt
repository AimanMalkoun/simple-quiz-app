package com.aimanhamza.simplequizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    var username = ""
    var score = 0
    fun setName(user:String){
        username = user
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.fragment, QuizQuestionsFragment()).commit()

    }

    fun setCscore(sc:Int){
        score = sc
        Toast.makeText(this, score.toString(), Toast.LENGTH_SHORT).show()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        if(score >= 7){
            fragmentTransaction.replace(R.id.fragment, ResultFragment(score, username)).commit()
        }else{
            fragmentTransaction.replace(R.id.fragment, ResultFragmentFail(score, username)).commit()
        }
    }

    fun goToHome(){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.replace(R.id.fragment, HomeFragment()).commit()
    }
}