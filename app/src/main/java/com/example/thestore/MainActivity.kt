package com.example.thestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.thestore.PrudectAdapter
import com.example.thestore.Prudects
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var adapter: PrudectAdapter
     var prudectarray=ArrayList<Prudects>()
    var productsSerch=ArrayList<Prudects>()

    var posi: MutableMap<Int, Int> = mutableMapOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var srl=findViewById<SwipeRefreshLayout>(R.id.srl)
        srl.setOnRefreshListener {
            srl.isRefreshing=false
        }
        var p1=Prudects("1","Microsoft Surface",1200,R.drawable.microsoftsurace,"Laptop - Intel Core i5 1035G7 Ice Lake, touchscreen 13.5 2256 × 1504, RAM 8GB LPDDR4X, Intel Iris Plus Graphics, SSD 128GB, webcam, USB-C, Windows 10 Home",0)
        var p2=Prudects("2","Gigabyte G7 KC",1800,R.drawable.gigabyteg7,"Gaming Laptop - Intel Core i7 10870H Comet Lake, 17.3 IPS anti-glare 1920 × 1080 144 Hz, RAM 16GB DDR4, NVIDIA GeForce RTX 3060 6GB, SSD 512GB, numeric keypad, webcam, USB 3.2 Gen 1, USB 3.2 Gen 2, 48,96 Wh battery, Windows 10 Home",0)
        var p3=Prudects("3","Lenovo Legion",1300,R.drawable.lenovolegion,"Gaming Laptop - AMD Ryzen 5 5600H, 15.6 IPS anti-glare 1920 × 1080 165 Hz, RAM 16GB DDR4, NVIDIA GeForce RTX 3060 6GB, SSD 512GB, numeric keypad, backlit keyboard, webcam, USB-C, 4-cell battery of 80 Wh, Without Operating System",0)
        var p4=Prudects("4","Dell Alienware Area-51m R2",3500,R.drawable.dellalienware,"Gaming Laptop - Intel Core i7 10700 Comet Lake, 17.3 IPS anti-glare 1920 × 1080 300 Hz, RAM 32GB DDR4, NVIDIA GeForce RTX 2070 Super 8GB, SSD 2000GB, numeric keypad, backlit keyboard, webcam, USB 3.2 Gen 1, 6-cell battery of 7895 mAh, Windows 10 Home (NBD) Product info: EN keyboard & OS settings (changeable during initial setup)",0)
        var p5=Prudects("5","Asus StudioBook 17 H700GV",2000,R.drawable.asusstudiobook,"Laptop - Intel Core i7 9750HF Coffee Lake, 17\" IPS matte 1920 × 1200, RAM 16GB DDR4, NVIDIA GeForce RTX 2060 6GB, SSD 1000GB, backlit keyboard, webcam, USB 3.2 Gen 1, USB-C, fingerprint reader, 3-cell battery of 57 Wh, Windows 10 Home H700GV-AV075T",0)
        var p6=Prudects("6","Dell G3 15 Gaming (3500)",1400,R.drawable.dellg3,"Gaming Laptop - Intel Core i7 10750H Comet Lake, 15.6\" WVA anti-glare 1920 × 1080 120 Hz, RAM 16GB DDR4, NVIDIA GeForce GTX 1660 Ti 6GB, SSD 512GB, numeric keypad, backlit keyboard, webcam, USB 3.2 Gen 1, USB-C, fingerprint reader, 4-cell battery of 68 Wh, Windows 10 Home (NBD)",0)


        prudectarray= arrayListOf<Prudects>(p1,p2,p3,p4,p5,p6)
        productsSerch.addAll(prudectarray)

        adapter=PrudectAdapter(productsSerch)


        adapter.prudectListener= object : PrudectAdapter.IPrudectListener {
            override fun counterBtn(counter:Int,position: Int,amounttxt:Int) {

                  cartCount.text = counter.toString()


                posi[position] = amounttxt
                for (v in posi.keys) {
                    if(posi[v]==0){

                    Log.d("ttt", "key: $v = ${posi[v]} ")}


                }

            }

            override fun decBtn(counter: Int, position: Int, amounttxt: Int) {

                cartCount.text=counter.toString()
              //  posi[position] = amounttxt
//                if (amounttxt == 0) {
//                    posi.remove(position)
               // }
                for (v in posi.keys) {


                    Log.d("ttt", "key: $v = ${posi[v]} ")

                }




            }

            override fun itemClicked(position: Int) {
                var intent = Intent(applicationContext, PrudectDetailsActivity::class.java)
                intent.putExtra("prudects",  productsSerch[position])
                startActivity(intent)            }


        }
        recyclerView.adapter=adapter



        var linearLayoutManager=
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager

//        var dividerItemDecoration= DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
//        // var appname=resources.getString(R.string.app_name)
//        var shape= ContextCompat.getDrawable(applicationContext,R.drawable.dividershape)
//        if (shape != null) {
//            dividerItemDecoration.setDrawable(shape)
//        }
//        recyclerView.addItemDecoration(dividerItemDecoration)


        var myspinner=findViewById<Spinner>(R.id.myspinner)
        var entries= arrayListOf<String>()
        entries.add("Sort by Price DES")
        entries.add("Sort by Price ASC")
        var spinnerAdapter= ArrayAdapter<String>(applicationContext,android.R.layout.simple_spinner_item,entries)
        myspinner.adapter=spinnerAdapter
        myspinner.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position==0){
                    var sorted=productsSerch.sortedWith(compareByDescending{it.price})
                    for(i in 0..sorted.lastIndex){
                        productsSerch[i]=sorted[i]
                    }
                    adapter.notifyDataSetChanged()
                }else{
                    var sorted=productsSerch.sortedWith(compareBy{it.price})
                    for(i in 0..sorted.lastIndex){
                      productsSerch[i]=sorted[i]
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        var searchView=findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productsSerch.clear()
                if(newText!!.isNotEmpty()){


                    var search=newText.toLowerCase(Locale.getDefault())
                    prudectarray.forEach {
                        if(it.prudectName?.toLowerCase(Locale.getDefault())?.contains(search) == true){
                            productsSerch.add(it)

                        }
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.d("ttt",productsSerch.toString())

                }else{
                    productsSerch.clear()
                    productsSerch.addAll(prudectarray)
                    recyclerView.adapter?.notifyDataSetChanged()
                    Log.d("ttt",productsSerch.toString())

                }
                return true

//               adapter.filter.filter(newText)
//
//
                return true
            }

        })
//


            cartImg.setOnClickListener {

                            if (cartCount.text.toString().toInt() > 0) {
                var intent = Intent(applicationContext,CartContActivity::class.java)
                val args = Bundle()

                args.putSerializable("ARRAYLIST", productsSerch as Serializable)
                args.putSerializable("map", posi as Serializable)
                intent.putExtra("BUNDLE", args)
                startActivity(intent)

//
            } else {
                Toast.makeText(applicationContext, "Your cart is empty", Toast.LENGTH_LONG).show()
            }
            }

    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.mymenu, menu)
//        val menuItem=menu!!.findItem(R.id.searchView)
//        if(menuItem!=null){
//
//            var searchView=menuItem.actionView as SearchView
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    productsSerch.clear()
//                    if(newText!!.isNotEmpty()){
//
//
//                        var search=newText.toLowerCase(Locale.getDefault())
//                        prudectarray.forEach {
//                            if(it.prudectName?.toLowerCase(Locale.getDefault())?.contains(search) == true){
//                                productsSerch.add(it)
//
//                            }
//                        }
//                        recyclerView.adapter?.notifyDataSetChanged()
//                        Log.d("ttt",productsSerch.toString())
//
//                    }else{
//                        productsSerch.clear()
//                        productsSerch.addAll(prudectarray)
//                        recyclerView.adapter?.notifyDataSetChanged()
//                        Log.d("ttt",productsSerch.toString())
//
//                            }
//                    return true
////                  adapter.filter.filter(newText)
////                    adapter.notifyDataSetChanged()
////                    return true
//                }
//
//            })
//        }
//
//        return super.onCreateOptionsMenu(menu)
//    }
}