package com.example.collegexp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrendingAdapter(private val events: List<TrendingEvent>) :
    RecyclerView.Adapter<TrendingAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val logo: ImageView = view.findViewById(R.id.imgLogo)
        val poster: ImageView = view.findViewById(R.id.imgPoster)
        val university: TextView = view.findViewById(R.id.txtUniversity)
        val event: TextView = view.findViewById(R.id.txtEvent)
        val time: TextView = view.findViewById(R.id.txtTime)
        val txtLikes: TextView = view.findViewById(R.id.txtLikes)

        val like: ImageButton = view.findViewById(R.id.btnLike)
        val comment: ImageButton = view.findViewById(R.id.btnComment)
        val share: ImageButton = view.findViewById(R.id.btnShare)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trending_event, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = events.size

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = events[position]

        // Display event information
        holder.university.text = item.university
        holder.event.text = item.event
        holder.time.text = item.time
        holder.txtLikes.text = item.likes.toString()

        // Display images
        holder.logo.setImageResource(item.logo)
        holder.poster.setImageResource(item.poster)

        // LIKE BUTTON
        holder.like.setOnClickListener {

            if (item.liked) {

                item.likes--
                item.liked = false

            } else {

                item.likes++
                item.liked = true

            }

            holder.txtLikes.text = item.likes.toString()
        }

        // CLICK EVENT CARD
        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                EventDetailsActivity::class.java
            )

            intent.putExtra("eventName", item.event)
            intent.putExtra("university", item.university)
            intent.putExtra("time", item.time)
            intent.putExtra("poster", item.poster)
            intent.putExtra("logo", item.logo)

            holder.itemView.context.startActivity(intent)
        }
    }
}
