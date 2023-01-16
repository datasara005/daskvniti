package com.example.projectb

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class play : Fragment() {
    private var totalTime: Int = 0
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    lateinit var scroll: RecyclerView
    lateinit var playbtn:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val K = inflater.inflate(R.layout.fragment_play, container, false)
        playbtn=K.findViewById(R.id.playbutton)
        playbtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.despasito)
        playbtn.setOnClickListener {
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start()
                playbtn.setImageResource(R.drawable.ic_baseline_pause_24)
            }
            else{
                mediaPlayer.pause()
                playbtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }
        layoutManager = LinearLayoutManager(requireContext())
        scroll = K.findViewById(R.id.scroll)
        scroll.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        scroll.adapter = adapter
        return K
    }


}

