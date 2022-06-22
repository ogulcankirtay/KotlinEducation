package com.ogulcankirtay.alertmesage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ogulcankirtay.alertmesage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Toast.makeText(this,"Welcome",Toast.LENGTH_LONG).show()
    }
    fun save(view: View){
        val alert=AlertDialog.Builder(this)
        alert.setTitle("Save")
        alert.setMessage("are you sure?")
        alert.setNegativeButton("no"){dialog, which->
            Toast.makeText(this, "Not saved", Toast.LENGTH_LONG).show()
        }
        alert.setPositiveButton("yes"){dialog,which->
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
        }
        alert.show()
    }
}