package com.ogulcank.recipebook

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    var yemekIsmiListei= ArrayList<String>()
    var yemekIdlistesi=ArrayList<Int>()
    private lateinit var ListAdapter: ListRcyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ListAdapter= ListRcyclerAdapter(yemekIsmiListei,yemekIdlistesi)
        recyclerView2.layoutManager=LinearLayoutManager(context)
        recyclerView2.adapter=ListAdapter
        sqlVerialma()
    }
    fun sqlVerialma(){
        try {
            activity?.let {
                val database=it.openOrCreateDatabase("Yemekler", Context.MODE_PRIVATE,null)

                val cursor=database.rawQuery("Select * From yemekler",null)
                val yemekIsmiIndex=cursor.getColumnIndex("yemekismi")
               val yemekIdIndex=cursor.getColumnIndex("id")
                yemekIsmiListei.clear()
                yemekIdlistesi.clear()
                while(cursor.moveToNext()){
                yemekIsmiListei.add(cursor.getString(yemekIsmiIndex))
                yemekIdlistesi.add(cursor.getInt(yemekIdIndex))
                }
                //veri geldikçe gösrermeye yarıyor
                ListAdapter.notifyDataSetChanged()
    cursor.close()
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}