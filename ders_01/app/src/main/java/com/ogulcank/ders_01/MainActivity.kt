package com.ogulcank.ders_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("merhaba dünya!")
        println(5*10)
        // yorum satiri
        var a=13
        println(a)
        //sabit tanımlama val
        val pi=3.14
        var intbelirtme: Int
        intbelirtme=12
        var longDeger : Long=20000
        println(longDeger)
        val floatPi : Float=3.1444444f
        println(floatPi)
        val myString="my String"
        println(myString.length)
        val ad="oğulcan"
        val soyad="kırtay"
        val full=ad+" "+soyad
        println(full)
        var myBool=true
        myBool=false
        var sayi_string="10"
        println(sayi_string.toInt())
        //array
        val myArray=arrayOf("a","b","d",1)
        println(myArray[myArray.size-1])
        println(myArray.get(0))
        myArray.set(0,"merhaba")
        println(myArray[0])
        val numberArray= doubleArrayOf(1.0,2.1,3.3)
        println(numberArray.get(2))
        // arrayliist
        val isimListesi= arrayListOf("olcan","ahmet","ayşe","ikra")
        //val isimlistesi=arrayListof<String>()
        println(isimListesi[0])
        isimListesi.add("halil")
        println(isimListesi[isimListesi.size-1])

        val karisikArraylist= arrayListOf<Any>()
        karisikArraylist.add("a")
        karisikArraylist.add(4)
        karisikArraylist.add(true)
        val intArraylist=ArrayList<Int>()
        intArraylist.add(5)
        intArraylist.add(20)

        println("--set---")
        val setdegerleri= setOf<Int>(1,1,2,3,2,4,3,5)
        var i=0
        setdegerleri.forEach{
            println("set ${i} .degeri ${it}")
            i+=1
        }
        val hashsetdegerleri= hashSetOf<Any>()
        hashsetdegerleri.add(1)
        hashsetdegerleri.add("a")
        hashsetdegerleri.add("a")
        hashsetdegerleri.forEach{
            println(it)
        }
    println("--hashmap--")
       //map ==> key -> value like dictinary
        val yemekkalori= hashMapOf<String,Int>()
        yemekkalori.put("elma",100)
        yemekkalori.put("et",300)
        yemekkalori.put("tavuk",200)
        println("etin kalorisi: ${yemekkalori["et"]}")
        val otherHashmap=HashMap<String,Int>()
        otherHashmap.put("key",1)
        val eklemeyolu= hashMapOf<String,String>("a" to "b","b" to "c")
        println(eklemeyolu.get("a"))
        // if statement
        val sayi=5
        if(sayi >=5 && sayi<=10){
            println("sayi 5-10 arasinda")
        }
        else{
            println("sayi 5 - 10 arasinda değil")
        }
        // when ->like switch case:
        var notDegeri=0
        var notDurumu: String

        when(notDegeri) {
            0->notDurumu="başarısız"
            1->notDurumu="zayıf"
            2->notDurumu="kötü"
            3->notDurumu="orta"
            4->notDurumu="iyi"
            else->notDurumu="çok iyi"

        }
        println(notDurumu)
    var dizi= arrayListOf(1,2,3,4,5,6)
        for(i in dizi){
            println(i)
        }
        for(i in 0..9){
            println(i)
        }
        var j=0
        while(j<10){
            println(j)
            j++
        }
    }
}