package com.ogulcank.superkahramanprojesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun olustur(view: View){
val isim=adText.text.toString()
        val yas=yasText.text.toString().toIntOrNull()
        val meslek=meslekText.text.toString()
        if(yas!=null) {
            var sKhrmn = SuperKahraman(isim,yas,meslek)
            textView.text="İsim: ${sKhrmn.isim} \nYas: ${sKhrmn.yas} \nMeslek:${sKhrmn.meslek}"
        }else{
    textView.text="Dogru yaşı giriniz"
        }
    }
}