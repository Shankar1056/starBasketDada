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
import apextechies.starbasketseller.model.ProductListDataModel

class ProductListAdapter(private val context: Context, private var catName: ArrayList<ProductListDataModel>, private val categorylist_row: Int, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var tvProductName: TextView
        internal var tvUnitCount: TextView
        internal var editTV: TextView
        internal var deleteTV: TextView
        internal var addTV: TextView
        internal var consLayout: ConstraintLayout

        init {
            tvProductName = view.findViewById<View>(R.id.tvProductName) as TextView
            tvUnitCount = view.findViewById<View>(R.id.tvUnitCount) as TextView
            editTV = view.findViewById<View>(R.id.editTV) as TextView
            deleteTV = view.findViewById<View>(R.id.deleteTV) as TextView
            addTV = view.findViewById<View>(R.id.addTV) as TextView
            consLayout = view.findViewById<View>(R.id.consLayout) as ConstraintLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(categorylist_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvProductName.setText(catName[position].name)
        holder.tvUnitCount.setText(""+catName[position].unitdetails!!.size)

        holder.itemView.setOnClickListener {
            if (holder.consLayout.visibility == View.VISIBLE)  holder.consLayout.visibility = View.GONE
            else holder.consLayout.visibility = View.VISIBLE
            holder.editTV.setOnClickListener {
                onItemClickListener.onClick(position, "edit")
            }
            holder.deleteTV.setOnClickListener {
                onItemClickListener.onClick(position, "delete")
            }
            holder.addTV.setOnClickListener {
                onItemClickListener.onClick(position, "add")
            }

        }

    }

    override fun getItemCount(): Int {
        return catName.size
    }
}