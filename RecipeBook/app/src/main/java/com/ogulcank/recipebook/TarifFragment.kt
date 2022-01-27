package com.ogulcank.recipebook

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.system.OsConstants
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_tarif.*
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.security.Permission
import java.util.jar.Attributes
import java.util.jar.Manifest


class TarifFragment : Fragment() {
    var secilenGorsel : Uri? = null
    var secilenBitmap : Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tarif, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener{
            kaydet(it)
        }
        imageView.setOnClickListener{
            gorselSec(it)
        }
        arguments?.let {

            var gelenBilgi = TarifFragmentArgs.fromBundle(it).bilgi

            if (gelenBilgi.equals("menuden geldim")){
                //yeni bir yemek eklemeye geldi
                    println("ifde")
                NameText.setText("")
                MalzemeText.setText("")
                RecipeText.setText("")
                button.visibility = View.VISIBLE

                val gorselSecmeArkaPlani = BitmapFactory.decodeResource(context?.resources,R.drawable.image)
                imageView.setImageBitmap(gorselSecmeArkaPlani)

            } else {
                println("elsde")
                //daha önce oluşturulan yemeği görmeye geldi
                button.visibility = View.INVISIBLE

                val secilenId = TarifFragmentArgs.fromBundle(it).id

                context?.let {

                    try {

                        val database=it.openOrCreateDatabase("Yemekler", Context.MODE_PRIVATE,null)

                        val cursor=database.rawQuery("Select * From yemekler Where id=?",arrayOf(secilenId.toString()))

                        val yemekIsmiIndex = cursor.getColumnIndex("yemekismi")
                        val yemekMalzemeIndex = cursor.getColumnIndex("malzeme")
                        val tarif=cursor.getColumnIndex("tarif")
                        val yemekGorseli = cursor.getColumnIndex("gorsel")
                        println(secilenId)
                        while(cursor.moveToNext()){
                            println("yemek ismi oo")
                            println(cursor.getString(yemekIsmiIndex))
                            NameText.setText(cursor.getString(yemekIsmiIndex))
                            MalzemeText.setText(cursor.getString(yemekMalzemeIndex))
                            RecipeText.setText(cursor.getString(tarif))
                            val byteDizisi = cursor.getBlob(yemekGorseli)
                            val bitmap = BitmapFactory.decodeByteArray(byteDizisi,0,byteDizisi.size)
                            imageView.setImageBitmap(bitmap)
                        }

                        cursor.close()

                    } catch (e: Exception){
                        e.printStackTrace()
                    }

                }

            }

        }


    }

 fun kaydet(view: View){

     //Sqllite  Save
     val Yemekismi=NameText.text.toString()
     val YemekMalzemeler=MalzemeText.text.toString()
     val tarif=RecipeText.text.toString()

     if(secilenBitmap!=null){
         val littlebitmap=littlebitmapcreate(secilenBitmap!!,300)
     val outputStream=ByteArrayOutputStream()
     littlebitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
         val byteDizisi=outputStream.toByteArray()
         try {
             context?.let {
                 val database=it.openOrCreateDatabase("Yemekler", Context.MODE_PRIVATE,null)
                    database.execSQL("Create Table If Not Exists yemekler (id INTEGER PRİMARY KEY,yemekismi VARCHAR,malzeme VARCHAR,tarif VARCHAR,gorsel BLOB)")
                 val sqlString="Insert into yemekler (yemekismi,malzeme,tarif,gorsel) Values(?,?,?,?)"
             val statement=database.compileStatement(sqlString)
                 statement.bindString(1,Yemekismi)
                 statement.bindString(2,YemekMalzemeler)
                 statement.bindString(3,tarif)
                 statement.bindBlob(4,byteDizisi)
                 statement.execute()
             }
         }catch (e: Exception){
             e.printStackTrace()
         }
         val action=TarifFragmentDirections.actionTarifFragmentToListFragment()
         Navigation.findNavController(view).navigate(action)
     }

    }
    fun gorselSec(view: View){

        activity?.let {
            if (ContextCompat.checkSelfPermission(it.applicationContext,android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                //izin verilmedi, izin istememiz gerekiyor
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
            } else {
                //izin zaten verilmiş, tekrar istemeden galeriye git
                val galeriIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //izni aldık
                val galeriIntent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)

            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == 2 && resultCode == Activity.RESULT_OK && data != null){

            secilenGorsel = data.data

            try {

                context?.let {
                    if(secilenGorsel != null) {
                        if( Build.VERSION.SDK_INT >= 28){
                            val source = ImageDecoder.createSource(it.contentResolver,secilenGorsel!!)
                            secilenBitmap = ImageDecoder.decodeBitmap(source)
                            imageView.setImageBitmap(secilenBitmap)
                        } else {
                            secilenBitmap = MediaStore.Images.Media.getBitmap(it.contentResolver,secilenGorsel)
                            imageView.setImageBitmap(secilenBitmap)
                        }

                    }
                }

            } catch (e: Exception){
                e.printStackTrace()
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
    fun littlebitmapcreate(secilenBitmap: Bitmap,MaxBoyut: Int): Bitmap{
        var  width=secilenBitmap.width
        var height=secilenBitmap.height
        val oran: Double=width.toDouble()/height.toDouble()
        if(oran>1){
            //gorsel yatay
        width=MaxBoyut
        val newHeigth=width/oran
            height=newHeigth.toInt()
        }
        else{
            //gorsel Dikey
            height=MaxBoyut
            var newWidht=height/oran
            width=newWidht.toInt()

        }
        return  Bitmap.createScaledBitmap(secilenBitmap,width, height,true)

    }
}