package com.example.thestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.thestore.Prudects

class PrudectDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prudect_details)
        var photid=findViewById<ImageView>(R.id.detailImg)
        var prudectName=findViewById<TextView>(R.id.detailitemName)
        var prudectDetail=findViewById<TextView>(R.id.detailitemdetailes)

        var p=intent.getParcelableExtra<Prudects>("prudects") as Prudects
        photid.setImageResource(p.photoID)
        prudectName.text=p.prudectName
        prudectDetail.text=p.description
    }
}