package com.ogulcank.photographsharingfirebase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.ogulcank.photographsharingfirebase.model.Post
import com.ogulcank.photographsharingfirebase.R
import com.ogulcank.photographsharingfirebase.adapter.FeedRecyclerAdapter
import kotlinx.android.synthetic.main.activity_fedd.*

class FeddActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseFirestore
    private lateinit var recyclerViewAdapter: FeedRecyclerAdapter
    var postlistesi=ArrayList<Post>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fedd)
        database= FirebaseFirestore.getInstance()
         auth=FirebaseAuth.getInstance()

        verial()

        // xml bağlıycaz
        val layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerViewAdapter= FeedRecyclerAdapter(postlistesi)
        recyclerView.adapter=recyclerViewAdapter
    }
    fun verial(){
        database.collection("Post").orderBy("time",Query.Direction.DESCENDING).addSnapshotListener { snapshot, exception ->
            if(exception!=null){
                Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(snapshot!=null){
                    if(!snapshot.isEmpty){
                        val docs=snapshot.documents
                        postlistesi.clear()
                        for(i in docs){
                            val email=i.get("userEmail") as String
                            val yorum=i.get("userComment") as String
                            val uri=i.get("uri") as String

                            val post= Post(email,uri,yorum)
                            postlistesi.add(post)
                        }

                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.options_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId== R.id.picShare){
            // share pic
            val intent=Intent(this, PotographSharingActivity::class.java)
            startActivity(intent)
        }else if(item.itemId== R.id.singOut){
                // sing out
            auth.signOut()
            intent= Intent(this, UserActivty::class.java)
            startActivity(intent)
            finish()


        }

        return super.onOptionsItemSelected(item)
    }

}