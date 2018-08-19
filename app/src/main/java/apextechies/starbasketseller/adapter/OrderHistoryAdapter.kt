package apextechies.starbasketseller.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import apextechies.starbasketseller.R
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.model.OrderHistoryDataModel

class OrderHistoryAdapter(private val context: Context, private var catName: ArrayList<OrderHistoryDataModel>, private val name: String, private val categorylist_row: Int, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var tvProductName: TextView
        internal var tvUnitCount: TextView
        internal var arrowIV: ImageView

        init {
            tvProductName = view.findViewById<View>(R.id.tvProductName) as TextView
            tvUnitCount = view.findViewById<View>(R.id.tvUnitCount) as TextView
            arrowIV = view.findViewById<View>(R.id.arrowIV) as ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(categorylist_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.arrowIV.visibility = View.GONE
        if (name.equals("New Order") && catName[position].order_status.equals("1")){
        holder.tvProductName.setText("Product Name: "+catName[position].product_name)
        holder.tvUnitCount.setText("Status: "+catName[position].order_status)
        }
        if (name.equals("Completed Order") && catName[position].order_status.equals("2")){
        holder.tvProductName.setText("Product Name: "+catName[position].product_name)
        holder.tvUnitCount.setText("Status: "+catName[position].order_status)
        }
        else if (name.equals("View Edit")){
        holder.tvProductName.setText("Product Name: "+catName[position].product_name)
        holder.tvUnitCount.setText("Status: "+catName[position].order_status)
        }

    }

    override fun getItemCount(): Int {
        return catName.size
    }

}