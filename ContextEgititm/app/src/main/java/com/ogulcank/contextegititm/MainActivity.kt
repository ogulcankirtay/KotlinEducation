package com.ogulcank.contextegititm

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    //Toast,Alert
        //applicationContext -> app context
        //this,this@Mainactivity same thing -> activity context
        Toast.makeText(this,"Hoşgeldiniz",Toast.LENGTH_LONG).show()
    }
    fun mesajGoster(view: View){
     val uyariMesaji=AlertDialog.Builder(this)
       uyariMesaji.setTitle("Şifre Hatası")
        uyariMesaji.setMessage("Hatali Şifre, Tekrar denemek ister misiniz")
        uyariMesaji.setPositiveButton("Evet",DialogInterface.OnClickListener{dialogInterface, i ->
            Toast.makeText(this ,"Tekrar Deniyorsunuz..", Toast.LENGTH_SHORT).show()
        })
        uyariMesaji.setNegativeButton("Hayir"){DialogInterface, i->
            Toast.makeText(this, "Hayiri Sectiniz", Toast.LENGTH_SHORT).show()
        }
        uyariMesaji.show()
    }
}