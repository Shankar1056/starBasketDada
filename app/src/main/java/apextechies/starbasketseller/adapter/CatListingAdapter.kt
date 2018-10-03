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
import java.util.*

class CatListingAdapter (private val context: Context, private var catName: ArrayList<CategoryDateModel>, private val categorylist_row: Int, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<CatListingAdapter.MyViewHolder>() {
   var list = ArrayList<CategoryDateModel>()
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var namrTV: TextView

        init {
            namrTV = view.findViewById<View>(R.id.namrTV) as TextView
        }


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(categorylist_row, parent, false)
        list = ArrayList<CategoryDateModel>()
        list.addAll(catName)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        if (catName[position].status.equals("1")) {
            holder.namrTV.setText(catName[position].name)
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position, catName[position].name!!)
        }

    }

    override fun getItemCount(): Int {
        return catName.size
    }

    fun filter(text: String) {

        var charText = text.toLowerCase(Locale.getDefault())
        catName.clear()
        if (charText.length == 0) {
            catName.addAll(list);
        } else {
            for(wp in list){
                if (wp!!.name!!.toLowerCase(Locale.getDefault())
                                .contains(charText)) {
                    catName.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }
}