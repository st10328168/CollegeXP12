package com.example.collegexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class EventAdapter(private var events: List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var filteredEvents: List<Event> = events

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val university: TextView = view.findViewById(R.id.txtUniversity)
        val eventName: TextView = view.findViewById(R.id.txtEvent)
        val eventTime: TextView = view.findViewById(R.id.txtTime)
        val poster: ImageView = view.findViewById(R.id.imgPoster)
        val logo: ImageView = view.findViewById(R.id.imgLogo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_trending_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = filteredEvents[position]
        holder.university.text = event.university
        holder.eventName.text = event.name
        holder.eventTime.text = "${event.date} • ${event.time}"
        holder.poster.setImageResource(event.image)
        // Since Event doesn't have a logo field, we use a placeholder or the same image
        holder.logo.setImageResource(R.drawable.ic_launcher_foreground)
    }

    override fun getItemCount(): Int = filteredEvents.size

    fun filter(query: String) {
        filteredEvents = if (query.isEmpty()) {
            events
        } else {
            val lowercaseQuery = query.lowercase(Locale.getDefault())
            events.filter {
                it.name.lowercase(Locale.getDefault()).contains(lowercaseQuery) ||
                        it.university.lowercase(Locale.getDefault()).contains(lowercaseQuery) ||
                        it.category.lowercase(Locale.getDefault()).contains(lowercaseQuery)
            }
        }
        notifyDataSetChanged()
    }
}
