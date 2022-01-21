package com.ogulcank.classandfunction

class SuperKahramanlar(var isim: String,var yas: Int, var meslek: String) {

/*
   //yukardaki primary constructor
    //property
    var isim=""
    var yas = 0
    var meslek=""
    constructor(isim: String,yas: Int,Meselek: String){
        this.isim=isim
        this.yas=yas
        this.meslek=meslek
        println("constructor çağırıldı...")
    }*/
private var sacrengi="yesil"
    //Getter
    fun sacrengiAl() : String{
        return  sacrengi
    }
    //Setter
    fun sacrengiDegistir(renk:String){
    this.sacrengi=renk
    }

}