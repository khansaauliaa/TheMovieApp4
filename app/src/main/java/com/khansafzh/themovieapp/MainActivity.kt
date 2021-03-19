package com.khansafzh.themovieapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.khansafzh.themovieapp.adapter.ViewPagerAdap
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        fun getLaunchService(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vp_viewpager.adapter = ViewPagerAdap(this, supportFragmentManager)
        tl_tab.setupWithViewPager(vp_viewpager)
        iv_profile_main.setOnClickListener(this)
    }
    fun onDataChange(snapshot: DataSnapshot) {
        for (p0 in snapshot.children){
            val name = snapshot.child("fullName").value.toString()
            val photo = snapshot.child("photo").value.toString()

            tv_name_profile.text = name
            Glide.with(this@MainActivity).load(photo).into(iv_profile)
        }
    }


    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.iv_profile -> {
                this.startActivity(Intent(this, ProfileActivity::class.java))
            }
        }

        fun onCreateOptionsMenu(menu: Menu): Boolean {
            val inflater: MenuInflater = menuInflater
            inflater.inflate(R.menu.main_menu, menu)
            return true
        }

        fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.iv_profile -> {
                    this.startActivity(Intent(this, ProfileActivity::class.java))
                    return true
                }
                else -> super.onOptionsItemSelected(item)
            }
        }
    }
}