package com.example.mvvmkotlinwithretrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmkotlinwithretrofit.R
import com.example.mvvmkotlinwithretrofit.model.Heroes


class MainAdapter(private val contex : Context, private var listItem: List<Heroes>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    private val mContext = contex
    private var mList = listItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view  =
            LayoutInflater.from(mContext).inflate(R.layout.row_recyclerview, parent, false);
        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = mList[position]
        Glide.with(mContext).load(item.imageurl).into(holder.imageView)
        holder.textViewName.text = item.name
    }

    fun update(list : List<Heroes>){
        mList = list
        notifyDataSetChanged()
    }
    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewLink: TextView = view.findViewById(R.id.textViewLink)
    }
}