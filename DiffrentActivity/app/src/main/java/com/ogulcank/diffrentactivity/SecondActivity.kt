package com.ogulcank.diffrentactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val a=intent
        val b = intent.getStringExtra("gonderilenVeri")
        textView2.text=b
    }
    fun Geridon(view: View){
        val a=Intent(applicationContext,MainActivity::class.java)
        startActivity(a)
        finish()

    }
}