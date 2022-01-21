package com.ogulcank.fragmentkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun f1(view: View){
        val fragmentmanager=supportFragmentManager
        val fragmentTransaction=fragmentmanager.beginTransaction()

        val firstfragment=BlankFragment()
        fragmentTransaction.replace(R.id.frameLayout,firstfragment).commit()
    }
    fun f2(view: View){
        val fragmentmanager=supportFragmentManager
        val fTrn=fragmentmanager.beginTransaction()

        val sfragment=BlankFragment2()
        fTrn.replace(R.id.frameLayout,sfragment).commit()
    }
}