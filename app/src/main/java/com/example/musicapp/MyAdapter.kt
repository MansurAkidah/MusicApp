package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Activity, val dataList : List<Data>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var currentMusic : MediaPlayer? = null
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val image : ImageView
        val title : TextView
        val play : ImageButton
        val pause : ImageButton
        val stop : ImageButton

        init {
            image = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.MusicTitle)
            play = itemView.findViewById(R.id.playButton)
            pause = itemView.findViewById(R.id.pauseButton)
            stop = itemView.findViewById(R.id.stop)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //create view as backup for the layout manager
        val itemView = LayoutInflater.from(context).inflate(R.layout.items, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {//displays the data at a specific position when called by the RecyclerView.
        // i.e populates the data into view


        val current = dataList[position]
        val mediaPlayer = MediaPlayer.create(context, current.preview.toUri())

        holder.title.text = current.title
        Picasso.get().load(current.album.cover).into(holder.image)

        mediaPlayer.setOnCompletionListener {
            val nextPosition = position + 1
            if (nextPosition < dataList.size) {
                val nextData = dataList[nextPosition]
                val nextMediaPlayer = MediaPlayer.create(context, nextData.preview.toUri())
                nextMediaPlayer.start()
            }
        }


        holder.play.setOnClickListener{
            mediaPlayer.start()
//            if (currentMusic != null) {
//                currentMusic?.stop()
//                currentMusic?.release()
//            }
//            // Create and start the new MediaPlayer for the current item.
//            val mediaPlayer = MediaPlayer.create(context, current.preview.toUri())
//            mediaPlayer.start()
//
//            // Set the global variable to the new MediaPlayer.
//            currentMusic = mediaPlayer

        }

        holder.pause.setOnClickListener{
            mediaPlayer.pause()
        }
        holder.stop.setOnClickListener{
            mediaPlayer.stop()
        }



    }

    override fun getItemCount(): Int {//returns the total number
        return dataList.size
    }
}