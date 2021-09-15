package com.example.thestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thestore.Prudects
//import com.example.thestore.Prudects

import com.example.thestore.R


class CartAdapter(var products:ArrayList<Prudects>, var map: MutableMap<Int,Int>):RecyclerView.Adapter<CartAdapter.SallaViewHolder>() {
    var prudect2= arrayListOf<Prudects>()
    init {
        for(k in map.keys){
            prudect2.add(products[k])
        }
//        for (i in 0..person.lastIndex){
//            Log.d("ert",person[i].toString())
//            if (!map.keys.contains(i)){
//                person.removeAt(i)
//            }
//        }
    }
    inner class SallaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img=itemView.findViewById<ImageView>(R.id.imgSalla)
        var name=itemView.findViewById<TextView>(R.id.nameSalla)
        var price=itemView.findViewById<TextView>(R.id.priceSalla)
        var number=itemView.findViewById<TextView>(R.id.resultNumber)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SallaViewHolder {
        return SallaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_pay,parent,false))
    }

    override fun onBindViewHolder(holder: SallaViewHolder, position: Int) {

       if(prudect2[position].price>0){ holder.name.text= prudect2[position].prudectName
        holder.img.setImageResource(prudect2[position].photoID)
        holder.price.text= "${prudect2[position].price}$"
        holder.number.text=map[position].toString()}
        
    }

    override fun getItemCount(): Int {
        return prudect2.size
    }

}