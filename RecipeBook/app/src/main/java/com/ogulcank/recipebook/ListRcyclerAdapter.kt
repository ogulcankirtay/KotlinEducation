package com.ogulcank.recipebook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_row.view.*

class ListRcyclerAdapter(val yemekListesi: ArrayList<String>,val idListesi: ArrayList<Int>) : RecyclerView.Adapter<ListRcyclerAdapter.YemekHolder>() {
class YemekHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YemekHolder {
 val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return YemekHolder(view)
    }

    override fun onBindViewHolder(holder: YemekHolder, position: Int) {
    holder.itemView.recyclerText.text=yemekListesi[position]
        holder.itemView.setOnClickListener{
        val action=ListFragmentDirections.actionListFragmentToTarifFragment("recyclerdan geldim",idListesi[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return yemekListesi.size
    }
}