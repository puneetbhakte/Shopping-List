package com.example.shoppinglist

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.shoppinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var listViewModel:ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewmodel()
        var adapter = Adapter(listViewModel)

        binding.rvdata.layoutManager = LinearLayoutManager(this, VERTICAL,false)
      //  val list = listViewModel.getalldata().value
        listViewModel.getalldata().observe(this){
            adapter.differ.submitList(it)
        }
        binding.rvdata.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
          AddList(this, object :AddDialogListner{
              override fun onAddButtonClicked(list: List) {
                       listViewModel.addlist(list)
              }

          } ).show()
        }


        }


    fun setupViewmodel(){
        var repository = ListRepository(ListDatabase(this))
       var viewModelProviderFactory = ViewModelFactory(application,repository)
        listViewModel = ViewModelProvider(this,viewModelProviderFactory)[ListViewModel::class.java]

    }
}