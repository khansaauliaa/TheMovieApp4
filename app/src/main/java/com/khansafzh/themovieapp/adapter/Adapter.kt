package com.khansafzh.themovieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khansafzh.themovieapp.R
import com.khansafzh.themovieapp.model.Model
import kotlinx.android.synthetic.main.movies_item.view.*

class ContentAdapt(
    private val listener: (Model) -> Unit
) : RecyclerView.Adapter<ContentAdapt.ViewHolder>() {

    private val data = ArrayList<Model>()

    fun setData(items: ArrayList<Model>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movies_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(data[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindViewHolder(model: Model, listener: (Model) -> Unit) {
            with(itemView) {
                if (model.thumb != null) {
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w92${model.thumb}")
                        .into(iv_movie)

                }

                tv_title.text = model.title
                itemView.setOnClickListener { listener(model) }
            }

        }
    }
}