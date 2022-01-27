package com.ogulcank.oopkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        println("---------Sınıflar-----------")

        val a=Kullanici("olcan",22)
        val b=Kullanici("asdad",25)

        println("---------Encapsulation-----------")

        val x=Sanatci("x",24,"Müzisyen")
        println(x.isim)

        //x.isim="sad"-> seti private değiştirilemez:D
        println(x.yas)
        x.cinsiyet="erkek"

        println("---------Inheritance-----------")
        // Kalıtım
        val y=OzelSanatci("y",19,"Tiyatrocu")
        y.sarkiSoyle()
        y.isim

        println("---------Polymorphism-----------")
            // Static Polymorphism
        val c=Islemler()
        println(c.carpma())
        println(c.carpma(2,3))
        println(c.carpma(2,3,4))

           // Dinamik Polymorphism

        val kedi=Hayvan()
        kedi.sesCikar()

        val kopek=Kopek()
        kopek.sesCikar()
        kopek.kopekFonk()

        println("---------Absraction & Interface-----------")
        a.insan()
        var gitarobj=gitar("salon")
        gitarobj.elektro=true
        gitarobj.marka="gitar mark"
        gitarobj.bilgi()

        println("---------Lambda Expressions(Gösterimi)-----------")

        yazdir("test")

        val yazdirLambda={string: String-> println(string)}
        yazdirLambda("test")
         val carpLmd={a: Int,b: Int ->a*b}
        println(carpLmd(5,9))

        val carpLmdV2: (Int,Int)->Int={a,b->a*b}
        println(carpLmdV2(5,8))
    }
    fun yazdir(string : String){
        println(string)
    }
}