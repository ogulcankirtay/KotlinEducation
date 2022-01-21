package com.ogulcank.suerkahramankitabi

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Veri Hazırlığı
        var superKahramanIsimleri=arrayListOf<String>("IronMan","CaptanAmerika","Spiderman","Hulk","Thor")

        // verimsiz Tanımlamalar
        val ironmanBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.ironman)
        val captanamerikaBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.captanamerica)
        val spidermanBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.sipederman)
        val hulkBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.hulk)
        val thorBitmap=BitmapFactory.decodeResource(applicationContext.resources,R.drawable.thor)

        val ironmanDrawbleId=R.drawable.ironman
        val captanamerikaDrawbleId=R.drawable.captanamerica
        val spidermanDrawbleId=R.drawable.sipederman
        val hulkDrawbleId=R.drawable.hulk
        val thorDrawbleId=R.drawable.thor
        var superKahramanDrawbleId=ArrayList<Int>()
        superKahramanDrawbleId.add(ironmanDrawbleId)
        superKahramanDrawbleId.add(captanamerikaDrawbleId)
        superKahramanDrawbleId.add(spidermanDrawbleId)
        superKahramanDrawbleId.add(hulkDrawbleId)
        superKahramanDrawbleId.add(thorDrawbleId)
//Adapter
        val laymanager=LinearLayoutManager(this)
        recyclerView.layoutManager=laymanager
        val adapter=RecyclerAdapter(superKahramanIsimleri,superKahramanDrawbleId)
        recyclerView.adapter=adapter
    }
}