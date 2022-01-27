package com.ogulcank.oopkotlin

class Kopek: Hayvan() {

    fun kopekFonk(){
        super.sesCikar()
    }
    override fun sesCikar(){
        println("Kopek Sınıfı")
    }
}