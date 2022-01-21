package com.ogulcank.sqlitelearning

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try{
    val veritabani=this.openOrCreateDatabase("Urunler", MODE_PRIVATE,null)
            veritabani.execSQL("Create TABLE IF NOT EXISTS urunler(id INTEGER PRIMARY KEY,isim VARCHAR(20),fiyat INT)")
           /* veritabani.execSQL("INSERT into urunler(isim,fiyat) Values('ayakkabi',100)")
            veritabani.execSQL("INSERT into urunler(isim,fiyat) Values('etek',80)")
            veritabani.execSQL("INSERT into urunler(isim,fiyat) Values('atki',50)")
            veritabani.execSQL("INSERT into urunler(isim,fiyat) Values('tshirt',60)")
            veritabani.execSQL("INSERT into urunler(isim,fiyat) Values('elbise',200)")
*/
            //veritabani.execSQL("Update urunler Set isim='bardak' Where id=2")
           // veritabani.execSQL("Delete From urunler Where id=6")

            val cursor=veritabani.rawQuery("Select * from urunler",null)
           // val cursor=veritabani.rawQuery("Select * from urunler Where isim Like 'e%'",null)


            val idColumIndex=cursor.getColumnIndex("id")
            val isimColumIndex=cursor.getColumnIndex("isim")
            val fiyatColumIndex=cursor.getColumnIndex("fiyat")

            while (cursor.moveToNext()) {
                println("id: ${cursor.getInt(idColumIndex)} , isim: ${cursor.getString(isimColumIndex)} fiyat: ${cursor.getInt(fiyatColumIndex)}")
            }
        cursor.close()
        }

            catch(e : Exception){
                e.printStackTrace()
            }
    }
}