package apextechies.starbasketseller.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import apextechies.starbasketseller.R
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.model.CategoryDateModel
import apextechies.starbasketseller.model.SubSubCategoryDateModel

class SubSubCatListingAdapter (private val context: Context, private var catName: ArrayList<SubSubCategoryDateModel>, private val categorylist_row: Int, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<SubSubCatListingAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var namrTV: TextView

        init {
            namrTV = view.findViewById<View>(R.id.namrTV) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(categorylist_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.namrTV.setText(catName[position].name)


        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position,"")
        }

    }

    override fun getItemCount(): Int {
        return catName.size
    }
}