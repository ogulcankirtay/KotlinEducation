package com.ogulcank.sharedprefencesedication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sharedPrefences: SharedPreferences
    var alinankullaniciadi: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefences=this.getSharedPreferences("com.ogulcank.sharedprefencesedication",
            MODE_PRIVATE)
    alinankullaniciadi=sharedPrefences.getString("kadi",null)
    if(alinankullaniciadi!=null){
        textView.text="alınanan kullanıcı adı ${alinankullaniciadi}"
    }
    }
    fun kaydet(view: View){
    var kullniciadi=editText.text.toString()
    if(kullniciadi==""){
        Toast.makeText(this, "Lütfen 1 Değer Giriniz", Toast.LENGTH_SHORT).show()
    }else{
        sharedPrefences.edit().putString("kadi",kullniciadi).apply()
        textView.text="kaydedilen kullanıcı adı ${kullniciadi}"
    }
    }
    fun sil(view: View){
        alinankullaniciadi=sharedPrefences.getString("kadi","")
        if(alinankullaniciadi!=null){
    textView.text="kullanici Adi: "
            sharedPrefences.edit().remove("kadi").apply()
        }
    }
}