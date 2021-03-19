package com.khansafzh.themovieapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_first_onboarding.*

class FirstOnboardingActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        fun getLaunchService (from: Context) = Intent(from, FirstOnboardingActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_onboarding)
        supportActionBar?.hide()

        tv_next_first.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.tv_next_first -> startActivity(Intent(SecondonboardingActivity.getLaunchService(this)))
        }
    }
}