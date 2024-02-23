package com.example.shoppinglist

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.databinding.SingleViewBinding
import java.security.acl.Owner
import java.util.Objects

class Adapter(private var listViewModel: ListViewModel): RecyclerView.Adapter<Adapter.viewholder>() {

    inner class viewholder(val itemBinding: SingleViewBinding):RecyclerView.ViewHolder(itemBinding.root)


    private var diffCallback = object: DiffUtil.ItemCallback<List>(){
        override fun areItemsTheSame(oldItem: List, newItem: List): Boolean {
           return oldItem.id == newItem.id  &&
                   oldItem.name == newItem.name &&
                   oldItem.amount == newItem.amount
        }

        override fun areContentsTheSame(oldItem: List, newItem: List): Boolean {
            return  oldItem==newItem
        }

    }

    var differ = AsyncListDiffer(this,diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
    return viewholder(
        SingleViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        var currentlist = differ.currentList[position]
        holder.itemBinding.amount.text = currentlist.amount.toString()
        holder.itemBinding.name.text = currentlist.name
        holder.itemBinding.plus.setOnClickListener {
            currentlist.amount++
            holder.itemBinding.amount.text = currentlist.amount.toString()
            listViewModel.addlist(currentlist)
        }
        holder.itemBinding.minus.setOnClickListener {
            currentlist.amount--
            holder.itemBinding.amount.text = currentlist.amount.toString()
            listViewModel.addlist(currentlist)
        }

        holder.itemBinding.delete.setOnClickListener {
            listViewModel.deletelist(currentlist)
        }




    }

    override fun getItemCount(): Int {
        return differ.currentList.size

    }

 //   fun setupViewmodel(){
   //     var repository = ListRepository(ListDatabase(context))
     //   var viewModelProviderFactory = ViewModelFactory(Application(),repository)
       // listViewModel = ViewModelProvider(owner,viewModelProviderFactory)[ListViewModel::class.java]

   // }

}