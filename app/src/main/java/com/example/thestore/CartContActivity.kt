package com.example.thestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class CartContActivity : AppCompatActivity() {
    lateinit var adapter: CartAdapter
    lateinit var products: ArrayList<Prudects>
    var posi: MutableMap<Int, Int> = mutableMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_cont)

        val intent = intent
        val args = intent.getBundleExtra("BUNDLE")
        products = args?.getSerializable("ARRAYLIST") as ArrayList<Prudects>
        posi = args?.getSerializable("map") as MutableMap<Int, Int>
        var rv = findViewById<RecyclerView>(R.id.rc)
        adapter = CartAdapter(products,posi)


        rv.adapter = adapter
        var linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        rv.layoutManager = linearLayoutManager
        for(i in 0..products.lastIndex){
            Log.d("ttt",products[i].ammount.toString())

        }
    }
}