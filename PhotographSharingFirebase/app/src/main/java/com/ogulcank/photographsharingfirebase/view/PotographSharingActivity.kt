package com.ogulcank.photographsharingfirebase.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.ogulcank.photographsharingfirebase.R
import kotlinx.android.synthetic.main.activity_potograph_sharing.*
import java.util.*

class PotographSharingActivity : AppCompatActivity() {
    var selectedImage: Uri?=null
    var selectedBitmap: Bitmap?=null
    private lateinit var storage: FirebaseStorage
    private lateinit var database: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_potograph_sharing)
        storage= FirebaseStorage.getInstance()
        auth= FirebaseAuth.getInstance()
        database= FirebaseFirestore.getInstance()
    }
    fun Share(view: View){
        val uuid=UUID.randomUUID()
        val ad="${uuid}.jpg"
        val reference=storage.reference
        val imageref=reference.child("images").child(ad)
        if(selectedImage!=null) {
            imageref.putFile(selectedImage!!).addOnSuccessListener { taskSnapshot->
                val uplImageref=FirebaseStorage.getInstance().reference.child("images").child(ad)

                uplImageref.downloadUrl.addOnSuccessListener {
                    val dowlanduri=it.toString()
                    val userEmail=auth.currentUser!!.email.toString()
                    val userComment=textComment.text.toString()
                    val time=Timestamp.now()

                    // database
                    val postHashmap= hashMapOf<String,Any>()
                    postHashmap.put("uri",dowlanduri)
                    postHashmap.put("userEmail",userEmail)
                    postHashmap.put("userComment",userComment)
                    postHashmap.put("time",time)

                    database.collection("Post").add(postHashmap).addOnCompleteListener{
                        if(it.isSuccessful){
                            finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
                    }

                }.addOnFailureListener {
                    Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }
        }
        }
fun selectImage(view : View){
if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
    //izin almamışız
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
}else{
    //izin zaten varsa
    val galeriIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    startActivityForResult(galeriIntent,2)
}
}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode==1){
            if(grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //izin verilinvce yapılacaklar
                val galeriIntent=Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntent,2)
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==2 && resultCode==Activity.RESULT_OK && data!=null){

            selectedImage=data.data
            if(selectedImage!=null){
                if(Build.VERSION.SDK_INT>=28){
                    val source=ImageDecoder.createSource(this.contentResolver,selectedImage!!)
                    selectedBitmap=ImageDecoder.decodeBitmap(source)
                    imageView2.setImageBitmap(selectedBitmap)
                }else{
                    selectedBitmap=MediaStore.Images.Media.getBitmap(this.contentResolver,selectedImage)
                    imageView2.setImageBitmap(selectedBitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
