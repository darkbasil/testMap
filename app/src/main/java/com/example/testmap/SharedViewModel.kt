package com.example.testmap

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testmap.utils.Pin
import com.example.testmap.utils.Root
import com.example.testmap.utils.ServiceAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class SharedViewModel : ViewModel() {
   private val _root = MutableLiveData<Root>().apply {
      value
   }
   val root: LiveData<Root> = _root

   fun initRoot(context: Context) {
      val jsonString = context.assets
         .open("pins.json")
         .bufferedReader().use {
            it.readText()
         }

      val moshi = Moshi.Builder()
         .add(ServiceAdapter())
         .build()
      val adapter: JsonAdapter<Root> = moshi.adapter(Root::class.java)
      _root.value = adapter.fromJson(jsonString)
   }
}