package apextechies.starbasketseller.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import apextechies.starbasketseller.R
import apextechies.starbasketseller.R.id.*
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.model.ProductUnitDetailsModel

class ProductVarientDeleteAdapter (private val context: Context, private var catName: ArrayList<ProductUnitDetailsModel>, private val categorylist_row: Int, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ProductVarientDeleteAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var tvprice: TextView
        internal var tvsellingPrice: TextView
        internal var editTV: TextView
        internal var deleteTV: TextView
        internal var tvDescription: TextView
        internal var consLayout: ConstraintLayout

        init {
            tvprice = view.findViewById<View>(R.id.tvprice) as TextView
            tvsellingPrice = view.findViewById<View>(R.id.tvsellingPrice) as TextView
            editTV = view.findViewById<View>(R.id.editTV) as TextView
            deleteTV = view.findViewById<View>(R.id.deleteTV) as TextView
            tvDescription = view.findViewById<View>(R.id.tvDescription) as TextView
            consLayout = view.findViewById<View>(R.id.consLayout) as ConstraintLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(categorylist_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvprice.setText("Actual Price: "+catName[position].actual_price)
        holder.tvsellingPrice.setText("Selling Price: "+ catName[position].selling_price)
        holder.tvDescription.setText("Short Description: "+ catName[position].short_description)

        holder.itemView.setOnClickListener {
            if (holder.consLayout.visibility == View.VISIBLE) holder.consLayout.visibility = View.GONE
            else holder.consLayout.visibility = View.VISIBLE
            holder.editTV.setOnClickListener {
                onItemClickListener.onClick(position, "edit")
            }
            holder.deleteTV.setOnClickListener {
                onItemClickListener.onClick(position, "delete")
            }

        }

    }

    override fun getItemCount(): Int {
        return catName.size
    }
}