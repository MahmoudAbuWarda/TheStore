package com.example.thestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thestore.Prudects
import com.example.thestore.CartAdapter
import java.util.ArrayList

class CartActivity : AppCompatActivity() {
    lateinit var adapter: CartAdapter
    lateinit var products: ArrayList<Prudects>
    var posi: MutableMap<Int, Int> = mutableMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)


        val intent = intent
        val args = intent.getBundleExtra("BUNDLE")
        products = args?.getSerializable("ARRAYLIST") as ArrayList<Prudects>
        posi = args?.getSerializable("map") as MutableMap<Int, Int>




        var recyclerView = findViewById<RecyclerView>(R.id.rc)
        adapter = CartAdapter(products,posi)
        recyclerView.adapter = adapter
        var linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager




    }
}