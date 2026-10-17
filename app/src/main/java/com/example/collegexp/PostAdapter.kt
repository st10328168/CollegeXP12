package com.example.collegexp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class PostAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val university: TextView =
            itemView.findViewById(R.id.txtUniversity)

        val caption: TextView =
            itemView.findViewById(R.id.txtCaption)

        val image: ImageView =
            itemView.findViewById(R.id.imgPost)

        val like: Button =
            itemView.findViewById(R.id.btnLike)

        val comment: Button =
            itemView.findViewById(R.id.btnComment)

        val share: Button =
            itemView.findViewById(R.id.btnShare)

        val report: ImageButton =
            itemView.findViewById(R.id.btnReport)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)

        return PostViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {

        val post = posts[position]

        // -------------------------
        // Post content
        // -------------------------

        holder.university.text = post.university
        holder.caption.text = post.caption
        holder.image.setImageResource(post.image)


        // -------------------------
        // University initial
        // -------------------------

        holder.itemView.findViewById<TextView>(
            R.id.txtUniversityInitial
        ).text = post.university
            .trim()
            .firstOrNull()
            ?.uppercase()
            ?: "U"


        // -------------------------
        // Like
        // -------------------------

        var liked = false
        var likes = post.likes

        holder.like.text = "♡ $likes"

        holder.like.setOnClickListener {

            liked = !liked

            if (liked) {
                likes++
                holder.like.text = "♥ $likes"

                Toast.makeText(
                    holder.itemView.context,
                    "Post liked",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                likes--
                holder.like.text = "♡ $likes"
            }
        }


        // -------------------------
        // Comments
        // -------------------------

        holder.comment.setOnClickListener {

            Toast.makeText(
                holder.itemView.context,
                "Comments coming soon",
                Toast.LENGTH_SHORT
            ).show()
        }


        // -------------------------
        // Share
        // -------------------------

        holder.share.setOnClickListener {

            val intent = Intent(Intent.ACTION_SEND).apply {

                type = "text/plain"

                putExtra(
                    Intent.EXTRA_TEXT,
                    "${post.university}\n\n${post.caption}"
                )
            }

            holder.itemView.context.startActivity(
                Intent.createChooser(
                    intent,
                    "Share CollegeXP Post"
                )
            )
        }


        // -------------------------
        // Report
        // -------------------------

        holder.report.setOnClickListener {

            Toast.makeText(
                holder.itemView.context,
                "Post reported",
                Toast.LENGTH_SHORT
            ).show()
        }


        // -------------------------
        // More options
        // -------------------------

        holder.itemView.findViewById<ImageButton>(
            R.id.btnMore
        ).setOnClickListener {

            Toast.makeText(
                holder.itemView.context,
                "More options",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = posts.size
}