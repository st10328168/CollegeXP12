package com.example.collegexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UniversityAdapter(
    private val originalList: List<University>
) : RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder>() {

    private var universityList = originalList.toMutableList()

    class UniversityViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val image: ImageView =
            itemView.findViewById(R.id.universityImage)

        val name: TextView =
            itemView.findViewById(R.id.universityName)

        val location: TextView =
            itemView.findViewById(R.id.universityLocation)

        val description: TextView =
            itemView.findViewById(R.id.universityDescription)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UniversityViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_university,
                parent,
                false
            )

        return UniversityViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: UniversityViewHolder,
        position: Int
    ) {

        val university = universityList[position]

        holder.image.setImageResource(university.image)
        holder.name.text = university.name
        holder.location.text = university.location
        holder.description.text = university.description

        holder.itemView.setOnClickListener {
            // We will open the university profile here next.
        }
    }

    override fun getItemCount(): Int {
        return universityList.size
    }

    fun filter(query: String) {

        universityList = if (query.isBlank()) {

            originalList.toMutableList()

        } else {

            originalList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.location.contains(query, ignoreCase = true)
            }.toMutableList()
        }

        notifyDataSetChanged()
    }
}