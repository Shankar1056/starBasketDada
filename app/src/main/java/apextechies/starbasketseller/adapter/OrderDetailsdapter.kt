package apextechies.starbasketseller.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import apextechies.starbasketseller.R
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.model.OrderetailsDataModel

class OrderDetailsdapter (private var catName: ArrayList<OrderetailsDataModel>) : RecyclerView.Adapter<OrderDetailsdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal var textItemName: TextView
        internal var textUnitPrice: TextView
        internal var textQuantity: TextView
        internal var texttotalprice: TextView

        init {
            textItemName = view.findViewById<View>(R.id.textItemName) as TextView
            textUnitPrice = view.findViewById<View>(R.id.textUnitPrice) as TextView
            textQuantity = view.findViewById<View>(R.id.textQuantity) as TextView
            texttotalprice = view.findViewById<View>(R.id.texttotalprice) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.orderdetails_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textItemName.setText(catName[position].product_name)
        holder.textUnitPrice.setText(catName[position].price)
        holder.textQuantity.setText(catName[position].quantity)
        holder.texttotalprice.setText(""+(Integer.parseInt(catName[position].quantity)) * (Integer.parseInt(catName[position].price)))



    }

    override fun getItemCount(): Int {
        return catName.size
    }
}