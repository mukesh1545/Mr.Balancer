package com.example.mrbalancer.Adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mrbalancer.R
import com.example.mrbalancer.RoomDataBase.BalancerData
import com.example.mrbalancer.Util.RupessFormat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class adapter(private val list: List<BalancerData>) : RecyclerView.Adapter<adapter.ViewAdapter>() {
    class ViewAdapter(view: View) : RecyclerView.ViewHolder(view) {
        var Type = view.findViewById<TextView>(R.id.Typeeach)
        var Tag = view.findViewById<TextView>(R.id.Amounteach)
        var Date = view.findViewById<TextView>(R.id.dateeach)
        var Remark = view.findViewById<TextView>(R.id.remarkeach)
        var img=view.findViewById<ImageView>(R.id.Updatebtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAdapter {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.eachtems, parent, false)
//        Log.d("Test","$list")
        Log.d("Adapter", "onCreateViewHolder")
        return ViewAdapter(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(holder: ViewAdapter, position: Int) {
        //Log.d("Test","$list")
        Log.d("Adapter", "onBindViewHolder position: $position")
        val item = list[position]
        Log.d("Adapter", "Item: $item")
        holder.Type.text = item.Type
       // holder.Tag.text = item.Amount.toString()
        holder.Date.text = item.Date
        holder.Remark.text = item.Remark
        // Format the amount in rupees
        holder.Tag.text = RupessFormat.rupessConverter(item.Amount).toString()
        if(item.Type == "Debited")
        {
            holder.Tag.setTextColor(ContextCompat.getColor(holder.Tag.context, R.color.credited))
        }
        else
        {
            holder.Tag.setTextColor(ContextCompat.getColor(holder.Tag.context, R.color.debited))
        }
            holder.Remark.text=item.Remark
            if (item.Tag == "Medical") {
                holder.img.setImageResource(R.drawable.medical)
            } else if (item.Tag == "Shopping") {
                holder.img.setImageResource(R.drawable.shopp)
            } else if (item.Tag == "Rent") {
                holder.img.setImageResource(R.drawable.rent)
            } else if(item.Tag=="Others"){
                holder.img.setImageResource(R.drawable.others)
            }
             else if(item.Tag=="Salary")
            {
                 holder.img.setImageResource(R.drawable.salary)
            }
             else{}




    }
}
