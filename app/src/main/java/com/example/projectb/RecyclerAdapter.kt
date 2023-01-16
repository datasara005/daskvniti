package com.example.projectb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var musicnames= arrayOf("Despasito","Чёрные глаза","Восточные Сказки","Suavemente","Dancing Lasha Tumbai","Blue","PPAP")
    private var singers= arrayOf("Luis Fonsi","Айдамир Мугу","Arash","Elvis Crespo","Verka Serduchka","Eiffel 65","PIKOTARO")
    private val images= intArrayOf(R.drawable.despasito,R.drawable.chornie,R.drawable.vastochnie,R.drawable.suavemente,R.drawable.dance,R.drawable.blue,R.drawable.ppap)
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int):RecyclerAdapter.ViewHolder {
        val v =LayoutInflater.from(parent.context).inflate(R.layout.cards,parent,false)
        return ViewHolder(v)

    }
    override fun getItemCount(): Int {
        return musicnames.size
    }
    override fun onBindViewHolder(holder:RecyclerAdapter.ViewHolder,position: Int){
        holder.itemTitle.text=musicnames[position]
        holder.itemDetail.text=singers[position]
        holder.itemImage.setImageResource(images[position])
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemImage:ImageView
        var itemTitle: TextView
        var itemDetail:TextView

        init {
            itemImage=itemView.findViewById(R.id.imageviewcard)
            itemTitle=itemView.findViewById(R.id.musicname)
            itemDetail=itemView.findViewById(R.id.singer)
        }


    }
}