package com.example.home.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.UniversityItemBinding
import com.example.home.domain.models.University

class UniversityAdapter(
    private val itemList: List<University>,
    private val onClick: (University) -> Unit,
) : RecyclerView.Adapter<UniversityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        // Inflate the custom layout
        val binding = UniversityItemBinding.inflate(LayoutInflater.from(context))
        // Return a new holder instance
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val university = itemList[position]
        // Set item name and state
        holder.bind(university, onClick)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(private val itemViewBinding: UniversityItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(university: University, onClick: (University) -> Unit) {
            itemViewBinding.tvName.text = university.name
            itemViewBinding.tvCountry.text = university.country
            itemViewBinding.root.setOnClickListener { onClick(university) }
        }

    }
}