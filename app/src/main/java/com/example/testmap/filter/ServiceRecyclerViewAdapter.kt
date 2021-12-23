package com.example.testmap.filter

import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testmap.R
import com.example.testmap.utils.Service

class ServiceRecyclerViewAdapter(private val services: List<Service>) :
    RecyclerView.Adapter<ServiceRecyclerViewAdapter.ServiceViewHolder>() {

    class ServiceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var textViewName: CheckedTextView? = null

        init {
            textViewName = itemView.findViewById(R.id.serviceName)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.service_item, parent, false)
        return ServiceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.textViewName?.text = services[position].name
        holder.textViewName?.isChecked = services[position].checked

        holder.textViewName?.setOnClickListener {
            services[position].checked = !services[position].checked
            holder.textViewName?.isChecked = services[position].checked
        }
    }

    override fun getItemCount(): Int {
        return services.size
    }
}