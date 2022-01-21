package com.ogulcank.suerkahramankitabi

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tanitim.*

class Tanitim : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanitim)
        val intent=intent
        val kahramanismi=intent.getStringExtra("superKahramanIsmi")
        textView.text=kahramanismi
        val secilengorsel=intent.getIntExtra("superKahramanGorselleri",0)
        val bitmap=BitmapFactory.decodeResource(applicationContext.resources,secilengorsel)
        imageView.setImageBitmap(bitmap)
    }
}