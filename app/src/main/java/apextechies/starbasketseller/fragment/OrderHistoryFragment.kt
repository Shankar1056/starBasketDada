package apextechies.starbasketseller.fragment

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.activity.OrderDetails
import apextechies.starbasketseller.adapter.OrderHistoryAdapter
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.OrderHistoryModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.fragment_order_hstory.*

@SuppressLint("ValidFragment")
class OrderHistoryFragment @SuppressLint("ValidFragment") constructor(private  val name: String) : Fragment() {
    private var retrofitDataProvider: RetrofitDataProvider? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_order_hstory, container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrofitDataProvider = RetrofitDataProvider(activity)
        RVproduct.layoutManager = LinearLayoutManager(activity)

        getOrderHistory()
    }

    private fun getOrderHistory() {
        Utilz.showDailog(activity, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.getSellerOrerList(ClsGeneral.getStrPreferences(activity, AppConstants.USERID),object : DownlodableCallback<OrderHistoryModel> {
            override fun onSuccess(result: OrderHistoryModel) {
                Utilz.closeDialog()
                if (result.status!!.contains(AppConstants.TRUE)) {
                    RVproduct.adapter = OrderHistoryAdapter(activity, result.data!!, name, R.layout.order_list_row, object : OnItemClickListener {
                        override fun onClick(pos: Int, text: String) {
                            if (name.equals("Order History")){
                                startActivity(Intent(activity, OrderDetails::class.java)
                                        .putExtra("orderid", result.data[pos].id)
                                        .putExtra("address_id", result.data[pos].address_id))
                            }
                        }

                    })
                }else{
                    if (activity.intent.getStringExtra("name").equals("New Order")){
                        noorderfound.setText("No New Order")
                    }else if (name.equals("Completed Order")){
                        noorderfound.setText("No Order Completed Yet")
                    }
                    else if (name.equals("View Edit")){
                        noorderfound.setText("No Order to View/Edit")
                    }
                    else if (name.equals("Order History")){
                        noorderfound.setText("No Order History Found")
                    }
                    noorderfound.visibility = View.VISIBLE
                }
            }
            override fun onFailure(error: String) {
                Utilz.closeDialog()
                Toast.makeText(activity, "" + error, Toast.LENGTH_SHORT).show()
            }

            override fun onUnauthorized(errorNumber: Int) {
                Utilz.closeDialog()
                Toast.makeText(activity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}