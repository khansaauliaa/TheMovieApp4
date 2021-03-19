package com.khansafzh.themovieapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        fun getLaunchService(from: Context) = Intent(from, ProfileActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()

        tv_logout.setOnClickListener(this)
        iv_back_profile.setOnClickListener(this)
    }

    fun onDataChange(snapshot: DataSnapshot) {
        for (p0 in snapshot.children){
            val name = snapshot.child("fullName").value.toString()
            val email = snapshot.child("email").value.toString()
            val photo = snapshot.child("photo").value.toString()

            tv_name_profile.text = name
            tv_email_profile.text = email
            Glide.with(this@ProfileActivity).load(photo).into(iv_profile)
        }
    }

    override fun onClick(p0: View) {
        when(p0.id) {
            R.id.tv_logout -> logOut()
            R.id.iv_back_profile -> MainActivity
        }
    }

    private fun logOut() {
        startActivity(Intent(
            SignInActivity.getLaunchService(
                this
            )
        ))
        FirebaseAuth.getInstance().signOut()
    }
}
