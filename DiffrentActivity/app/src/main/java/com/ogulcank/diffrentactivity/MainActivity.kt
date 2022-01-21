package com.ogulcank.diffrentactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("onCreate çalıştırıldı.")

    }

    override fun onStart() {
        super.onStart()
        println("onstart çalıştırıldı")
    }

    override fun onResume() {
        super.onResume()
        println("onResume çalıştırıldı.")

    }
    override fun onPause() {
        super.onPause()
        println("onPause çalıştırıldı.")
    }

    override fun onStop() {
        super.onStop()
        println("onStop çalıştırıldı.")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestory çalıştırıldı.")
    }
    fun Go_2_activity(view: View){

        val veri=editText.text.toString()
        val intent=Intent(applicationContext,SecondActivity::class.java)
        intent.putExtra("gonderilenVeri",veri)
        startActivity(intent)
        finish()
    }
}