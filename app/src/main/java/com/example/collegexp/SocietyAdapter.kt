package com.example.collegexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SocietyAdapter(
    private val originalList: List<Society>
) : RecyclerView.Adapter<SocietyAdapter.SocietyViewHolder>() {

    private var societyList = originalList.toMutableList()

    class SocietyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val image: ImageView =
            itemView.findViewById(R.id.societyImage)

        val name: TextView =
            itemView.findViewById(R.id.societyName)

        val category: TextView =
            itemView.findViewById(R.id.societyCategory)

        val university: TextView =
            itemView.findViewById(R.id.societyUniversity)

        val description: TextView =
            itemView.findViewById(R.id.societyDescription)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SocietyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_society,
                parent,
                false
            )

        return SocietyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: SocietyViewHolder,
        position: Int
    ) {

        val society = societyList[position]

        holder.image.setImageResource(society.image)
        holder.name.text = society.name
        holder.category.text = society.category
        holder.university.text = society.university
        holder.description.text = society.description
    }

    override fun getItemCount(): Int {
        return societyList.size
    }

    fun filter(query: String) {

        societyList = if (query.isBlank()) {

            originalList.toMutableList()

        } else {

            originalList.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true) ||
                        it.university.contains(query, ignoreCase = true)
            }.toMutableList()
        }

        notifyDataSetChanged()
    }
}
