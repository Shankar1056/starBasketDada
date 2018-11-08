package apextechies.starbasketseller.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import apextechies.starbasketseller.R
import apextechies.starbasketseller.R.id.namrTV
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.model.CategoryDateModel
import apextechies.starbasketseller.model.ImageModel
import com.squareup.picasso.Picasso
import java.util.*

class Imagedapter(private val context: Context, private val list: ArrayList<String>, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<Imagedapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var image: ImageView
        internal var deleteIV: ImageView

        init {
            image = view.findViewById<View>(R.id.image) as ImageView
            deleteIV = view.findViewById<View>(R.id.deleteIV) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        Picasso.with(context).load(list[position]).into(holder.image)
        holder.deleteIV.setOnClickListener {
            onItemClickListener.onClick(position, "")
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


}