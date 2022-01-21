package com.ogulcank.suerkahramankitabi

import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.recyler_row.view.*

class RecyclerAdapter(val KahramanListesi: ArrayList<String>,val KahramanGorselleri: ArrayList<Int>): RecyclerView.Adapter<RecyclerAdapter.SuperKahramanVH>() {
    class SuperKahramanVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperKahramanVH {
        //inflater,layoutInflater,MenuInflater
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.recyler_row,parent,false)
        return SuperKahramanVH(itemView)
    }

    override fun onBindViewHolder(holder: SuperKahramanVH, position: Int) {
    holder.itemView.recyclerViewTextView.text=KahramanListesi.get(position)
        holder.itemView.setOnClickListener{
            val intent=Intent(holder.itemView.context,Tanitim::class.java)
            intent.putExtra("superKahramanIsmi",KahramanListesi.get(position,))
            intent.putExtra("superKahramanGorselleri",KahramanGorselleri.get(position))
            holder.itemView.context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        return KahramanListesi.size
    }
}