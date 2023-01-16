package com.example.projectb

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max


class play : Fragment() {
    private var totalTime: Int = 0
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    lateinit var scroll: RecyclerView
    lateinit var playbtn:ImageView
    lateinit var volume:SeekBar
    lateinit var musicimage:ImageView
    lateinit var positionbar:SeekBar
    lateinit var lefttime:TextView
    lateinit var remainingtime:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val K = inflater.inflate(R.layout.fragment_play, container, false)
        playbtn=K.findViewById(R.id.playbutton)
        volume=K.findViewById(R.id.volume)
        musicimage=K.findViewById(R.id.musicphoto)
        positionbar=K.findViewById(R.id.positionbar)
        musicimage.setImageResource(R.drawable.musical_note)
        lefttime=K.findViewById(R.id.lefttime)
        remainingtime=K.findViewById(R.id.remainingtime)
        playbtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        volume.progress=100

        val mediaPlayer = MediaPlayer.create(requireContext(), R.raw.despasito)
        positionbar.max=totalTime
        positionbar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
        Thread(Runnable {
            while (mediaPlayer!=null){
                try {
                    var msg =Message()
                    msg.what=mediaPlayer.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                }catch (e:InterruptedException){

                }
            }
        }).start()
        playbtn.setOnClickListener {
            if (!mediaPlayer.isPlaying){
                musicimage.setImageResource(R.drawable.despasito)
                mediaPlayer.start()
                playbtn.setImageResource(R.drawable.ic_baseline_pause_24)
            }
            else{
                mediaPlayer.pause()
                playbtn.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }
        volume.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(SeekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser){
                    var volumeNum=progress/100.0f
                    mediaPlayer.setVolume(volumeNum,volumeNum)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        layoutManager = LinearLayoutManager(requireContext())
        scroll = K.findViewById(R.id.scroll)
        scroll.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        scroll.adapter = adapter
        return K
    }
    @SuppressLint("HandlerLeak")
    var handler=object :Handler(){
        override fun handleMessage(msg: Message) {
            var currentPosition= msg.what
            positionbar.progress=currentPosition
            var Lefttime=createTimeLabel(currentPosition)
            lefttime.text=Lefttime
            var RemainingTime=createTimeLabel(totalTime-currentPosition)
            remainingtime.text="-$RemainingTime"
        }
    }

    fun createTimeLabel(time:Int):String{
        var timeLabel=""
        var min =time/1000/60
        var sec= time/1000%60
        timeLabel="$min:"
        if (sec<10)timeLabel+="0"
        timeLabel+=sec
        return timeLabel
    }


}

