package com.example.mvvmkotlinwithretrofit.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmkotlinwithretrofit.R
import com.example.mvvmkotlinwithretrofit.adapter.MainAdapter
import com.example.mvvmkotlinwithretrofit.model.Heroes
import com.example.mvvmkotlinwithretrofit.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view : View
        val viewGroup : ViewGroup
        setViewmodel()
        setRecyclerView()

    }

     private fun setRecyclerView() {
        adapter = MainAdapter(this,viewModel.listData.value?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun setViewmodel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.listData.observe(this, dataObserver)
        viewModel.isViewLoading.observe(this,isViewLoadingObservable)
        viewModel.onMessageError.observe(this,isMessageErrorObservable)
        viewModel.isEmptyList.observe(this,emptyListObservable)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    //observer
    private val dataObserver = Observer<List<Heroes>> {
        Log.e(TAG, "data Updated = $it")
        layoutError.visibility = GONE
        layoutEmpty.visibility = GONE
        adapter.update(it)
    }

    private val isViewLoadingObservable = Observer<Boolean> {
        Log.e(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    @SuppressLint("SetTextI18n")
    private val isMessageErrorObservable = Observer<Any> {
        Log.e(TAG, "OnMessageError = $it")
        layoutError.visibility = VISIBLE
        layoutEmpty.visibility = GONE
        textViewError.text = "Error $it"
    }

    private val emptyListObservable = Observer<Boolean> {
        Log.e(TAG,"emptyListObservable $it")
        layoutError.visibility = GONE
        layoutEmpty.visibility = VISIBLE
    }
}
