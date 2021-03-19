package com.khansafzh.themovieapp.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.khansafzh.themovieapp.DetailActivity
import com.khansafzh.themovieapp.R
import com.khansafzh.themovieapp.adapter.ContentAdapt
import com.khansafzh.themovieapp.model.Model
import com.khansafzh.themovieapp.viewmodel.Content
import kotlinx.android.synthetic.main.fragment_movie.*

class MoviesFragment : Fragment() {
    private lateinit var content: Content
    private lateinit var contentAdapt: ContentAdapt

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoad(true)
        attachData()
        content.setCatalogueContentMovie()
    }

    @SuppressLint("FragmentLiveDataObserve")
    private fun attachData(){
        contentAdapt = ContentAdapt {
            showSelected(it)
        }

        contentAdapt.notifyDataSetChanged()
        rv_main.setHasFixedSize(true)
        rv_main.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //panggil RV
        rv_main.addItemDecoration(
            DividerItemDecoration(
                rv_main.context,
                DividerItemDecoration.VERTICAL
            )
        )
        rv_main.adapter = contentAdapt
        content = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(Content::class.java)
        content.getCatalogueContent().observe(this, Observer {
            contentAdapt.setData(it)
            showLoad(false)
        })
    }

    private fun showSelected(it: Model) {
        val page = Intent(context, DetailActivity::class.java)
        page.putExtra(DetailActivity.KEY_VALUE, it)
        startActivity(page)
    }

    private fun showLoad(b: Boolean) {
        if (b) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}
