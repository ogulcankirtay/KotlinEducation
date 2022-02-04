package com.ogulcank.photographsharingfirebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.ogulcank.photographsharingfirebase.R
import com.ogulcank.photographsharingfirebase.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recyler_row.view.*

class FeedRecyclerAdapter(val polist : ArrayList<Post>) : RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>(){
    class PostHolder(itemView: View): RecyclerView.ViewHolder(itemView){

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recyler_row,parent,false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
    holder.itemView.emailText.text=polist[position].email
    holder.itemView.yorumtext.text=polist[position].yorum
    Picasso.get().load(polist[position].url).into(holder.itemView.resim)
    }

    override fun getItemCount(): Int {
    return polist.size
    }
}