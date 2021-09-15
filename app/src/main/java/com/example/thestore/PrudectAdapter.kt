package com.example.thestore

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
//import android.widget.SpinnerAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.thestore.PrudectDetailsActivity
import com.example.thestore.R
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.goodslayout.view.*
import java.util.*
import kotlin.collections.ArrayList

class PrudectAdapter(var prudects: ArrayList<Prudects>):RecyclerView.Adapter<PrudectAdapter.PrudectViewHolder>()
 //  ,Filterable
{

    var counter:Int=0
    var prudectList = ArrayList<Prudects>()


    init {
        prudectList=prudects
    }
    lateinit var prudectListener:IPrudectListener
    class PrudectViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id=itemView.findViewById<TextView>(R.id.id)
        var prudectTitle=itemView.findViewById<TextView>(R.id.goodTitle)

        var prudectImg=itemView.findViewById<ImageView>(R.id.goodImg)
        var prudectPrice=itemView.findViewById<TextView>(R.id.goodPrice)
        var amountTxt=itemView.findViewById<TextView>(R.id.ammounTtext)
        var addBtn=itemView.findViewById<Button>(R.id.addGoodBtn)
        var subBtn=itemView.findViewById<Button>(R.id.subGoodBtn)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PrudectAdapter.PrudectViewHolder {
        var prudectView=LayoutInflater.from(parent.context).inflate(R.layout.goodslayout,parent,false)
        return PrudectViewHolder(prudectView)
    }



    override fun onBindViewHolder(holder: PrudectAdapter.PrudectViewHolder, position: Int) {
        var p=prudects[position]
        try {

            holder.id.text=prudectList[position].id
            holder.prudectTitle.text=prudectList[position].prudectName
            holder.prudectPrice.text="${prudectList[position].price.toString()}$"
            holder.prudectImg.setImageResource(prudectList[position].photoID)
            //  holder.amountTxt.text=prudectList[position].ammount.toString()
        }catch (ex:Exception){
            Log.d("ttt",ex.toString())
        }

        holder.itemView.setOnClickListener {
            var intent= Intent(holder.itemView.context,PrudectDetailsActivity::class.java)
            intent.putExtra("prudects",p)
            holder.itemView.context.startActivity(intent)
        }
        holder.subBtn.setOnClickListener {
            var amoutText=holder.amountTxt.text.toString().toInt()

            if(amoutText>=1) {
                holder.amountTxt.text = "${holder.amountTxt.text.toString().toInt() - 1}"

                counter--
                prudectListener.counterBtn(
                    counter,
                    position,
                    holder.amountTxt.text.toString().toInt()
                )
            }
            else{
                holder.subBtn.isActivated=false
            }

        }


        holder.itemView.setOnClickListener {
            prudectListener.itemClicked(position)
        }
        holder.addBtn.setOnClickListener {

            holder.amountTxt.text="${holder.amountTxt.text.toString().toInt()+1}"
            counter++

            prudectListener.counterBtn(counter,position,holder.amountTxt.text.toString().toInt())

//        prudectListener.counterBtn(position)
//            prudects.get(position).ammount++
//            holder.amountTxt.text=prudects.get(position).ammount.toString()



//            var prefShared=holder.itemView.context.getSharedPreferences("cartcounter", AppCompatActivity.MODE_PRIVATE)
//            if(prefShared !=null){
//
//                if(prefShared.contains("counter")){
//                    var counter=prefShared.getInt("counter",-1)
//
//                    counter++
//
//                    var editor=holder.itemView.context.getSharedPreferences("cartcounter", AppCompatActivity.MODE_PRIVATE).edit()
//                    editor.putInt("counter",counter)
//                    editor.commit()
//                    var intent=Intent(holder.itemView.context,MainActivity::class.java)
//                    // intent.putExtra("counter",counter)
//                    holder.itemView.context.startActivity(intent)
//                }
//                else{
//                    var counter=1
//                    var editor=holder.itemView.context.getSharedPreferences("cartcounter", AppCompatActivity.MODE_PRIVATE).edit()
//                    editor.putInt("counter",counter)
//                    editor.commit()
//                    var intent=Intent(holder.itemView.context,MainActivity::class.java)
//                    intent.putExtra("counter",counter)
//                    holder.itemView.context.startActivity(intent)
//
//                }
//            }





        }




    }

    override fun getItemCount(): Int {
        return prudects.size

    }
    interface IPrudectListener{
        public fun counterBtn(counter:Int,position: Int,amounttxt:Int)
        public fun decBtn(counter:Int,position: Int,amounttxt:Int)
        public fun itemClicked(position:Int)



    }


//    override fun getFilter(): Filter {
//        return  object :Filter(){
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                TODO("Not yet implemented")
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                TODO("Not yet implemented")
//            }
//
//        }

//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//
//                if (charSearch.isNullOrEmpty()) {
//                    prudectList = prudects
//                } else {
//                    var resultList = ArrayList<Prudects>()
//
//                    for (row in prudects) {
//
//                        if (row.prudectName.toString().lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT)) ) {
//                 //    if(row.prudectName.toString().toLowerCase().startsWith(charSearch.lowercase(Locale.ROOT))) {
//                            resultList.add(row)
//                        }
//
//                    }
//                    try {
//                        prudectList = resultList
//                        notifyDataSetChanged()
//                    }catch (ex:Exception){
//                        Log.d("ttt",ex.toString())}
//
//
//                }
//                val filterResults = FilterResults()
//                filterResults.values = prudectList
//
//
//                return filterResults
//
//            }
//            @Suppress("UNCHECKED_CAST")
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                Log.d("ttt","rrrrr${results?.values.toString()}")
//
//                try{prudectList = results?.values as ArrayList<Prudects>
//    notifyDataSetChanged()}catch (ex:Exception){
//        Log.d("ttt",ex.toString())
//    }
//
//
//            }
//
//
//
//        }
    }



//}