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
import apextechies.starbasketseller.R.id.arrowIV
import apextechies.starbasketseller.R.id.tvUnitCount
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.model.OrderHistoryDataModel

class OrderHistoryAdapter(private val context: Context, private var catName: ArrayList<OrderHistoryDataModel>, private val name: String, private val categorylist_row: Int, private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var tvTransactionId: TextView
        internal var tvDate: TextView

        init {
            tvTransactionId = view.findViewById<View>(R.id.tvTransactionId) as TextView
            tvDate = view.findViewById<View>(R.id.tvDate) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(categorylist_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (name.equals("New Order") && catName[position].order_status.equals("1")){
        holder.tvTransactionId.setText("Transaction Id: "+catName[position].transaction_id)
        holder.tvDate.setText("Order Date: "+catName[position].order_date)
        }
        if (name.equals("Completed Order") && catName[position].order_status.equals("2")){
        holder.tvTransactionId.setText("Transaction Id: "+catName[position].transaction_id)
        holder.tvDate.setText("Order Date: "+catName[position].order_date)
        }
        else if (name.equals("View Edit")){
        holder.tvTransactionId.setText("Transaction Id: "+catName[position].transaction_id)
        holder.tvDate.setText("Order Date: "+catName[position].order_date)
        }
        else if (name.equals("Order History")){
            holder.tvTransactionId.setText("Transaction Id: "+catName[position].transaction_id)
            holder.tvDate.setText("Order Date: "+catName[position].order_date)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(position,"")
        }

    }

    override fun getItemCount(): Int {
        return catName.size
    }

}