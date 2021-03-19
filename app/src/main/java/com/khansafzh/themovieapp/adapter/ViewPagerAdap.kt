package com.khansafzh.themovieapp.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.khansafzh.themovieapp.R
import com.khansafzh.themovieapp.fragment.MoviesFragment


class ViewPagerAdap(var context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return MoviesFragment()
        }
        return MoviesFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        if (position == 0) {
            return context.getString(R.string.text_1)
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        return 2
    }
}