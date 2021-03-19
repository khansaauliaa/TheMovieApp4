package com.khansafzh.themovieapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_third_onboarding.*

class ThirdOnboarding : AppCompatActivity(), View.OnClickListener {


    companion object{
        fun getLaunchService (from: Context) = Intent(from, ThirdOnboarding::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third_onboarding)
        supportActionBar?.hide()



        btn_get_started.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btn_get_started -> startActivity(Intent(SignUpActivity.getLaunchService(this)))
        }
    }
}