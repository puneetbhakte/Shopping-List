package com.example.shoppinglist


import android.content.Context

import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.databinding.ActivityAddListBinding

class AddList(context: Context, var addDialogListener: AddDialogListner) : AppCompatDialog(context) {
    lateinit var binding:ActivityAddListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddListBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val shoppingName = binding.etName.text.toString()
            val shoppingCount = binding.etAmount.text.toString()
            if(shoppingName.isEmpty()){

           //  Toast.makeText(this,"empty",Toast.LENGTH_LONG)
                return@setOnClickListener

            }
            val list = List(shoppingName,shoppingCount.toInt(),0)
            addDialogListener.onAddButtonClicked(list)
            dismiss()
        }
        binding.tvCancel.setOnClickListener {
            cancel()
        }

    }

}