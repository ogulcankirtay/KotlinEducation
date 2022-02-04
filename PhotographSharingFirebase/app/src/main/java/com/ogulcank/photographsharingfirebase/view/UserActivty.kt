package com.ogulcank.photographsharingfirebase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ogulcank.photographsharingfirebase.R
import kotlinx.android.synthetic.main.activity_main.*

class UserActivty : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth= FirebaseAuth.getInstance()

        val guncelkullanici=auth.currentUser
        if(guncelkullanici!=null){
            intent= Intent(this, FeddActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun girisYap(view: View){
        auth.signInWithEmailAndPassword(textEmailAddress.text.toString(),textPassword.text.toString()).addOnCompleteListener { task->
            if(task.isSuccessful){

                val guneclkullanici=auth.currentUser?.email.toString()
                Toast.makeText(this,"Hoşgeldiniz ${guneclkullanici}",Toast.LENGTH_LONG).show()
                val intent=Intent(this, FeddActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception->
            Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
    fun kayitOl(view: View){
        val email=textEmailAddress.text.toString()
        val password=textPassword.text.toString()

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task ->
            //asenkron
            if(task.isSuccessful){
                //go to other activity
                val intent=Intent(this, FeddActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
}