package com.ogulcank.classandfunction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firstFunction()
       // cikarma(100,20)
      //  var x=toplama(10,15)
     button.setOnClickListener(){
         text.text=toplama(20,35).toString()
     }
        sinifCalismasi()
        NullGuvenligi()
    }
    fun firstFunction(){
        println("first Function is runnig..")
    }
    fun cikarma(x: Int,y: Int){
    text.text="Sonuç: ${x-y}"
    }
    fun toplama(x: Int, y : Int): Int{
        return x+y
    }

    fun sinifCalismasi(){
        var superman= SuperKahramanlar("superman", 19,"gazeteci")

        text.text="isim: ${superman.sacrengiAl()}"
    }
    fun NullGuvenligi(){
        //Null,Nullability,Null Safety
        //Defining
        var str: String?
        //inizilation
        str="asda"
        var deger:Int?=null
        println(deger)
        //1
        if(deger!=null){
            println(deger*2)
        }else
        {
            println("null geldi")
        }
        //2
        //!!null olmayacak kesin ?->olabilir
        println(deger?.minus(2))
        //3 elvis operatörü
        val sonuc=deger?.minus(2) ?:10 //nullsa 10 yaz
        println(sonuc)
        //4 let eğer boşsa bişey yazmaz değilse işlem yapar
        deger?.let{
            println(it*5)
        }
    }
}