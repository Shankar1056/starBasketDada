package apextechies.starbasketseller.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.adapter.OrderHistoryAdapter
import apextechies.starbasketseller.adapter.ProductListAdapter
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.OrderHistoryModel
import apextechies.starbasketseller.model.ProductListModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_productlist.*
import kotlinx.android.synthetic.main.common_toolbar.*

class OrderHistory: AppCompatActivity() {
    private var retrofitDataProvider: RetrofitDataProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productlist)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        retrofitDataProvider = RetrofitDataProvider(this)
        RVproduct.layoutManager = LinearLayoutManager(this)

        toolbar.setNavigationOnClickListener {
            finish()
        }
        getOrderHistory()
    }

    private fun getOrderHistory() {
        Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.getSellerOrerList(ClsGeneral.getStrPreferences(this, AppConstants.USERID),object : DownlodableCallback<OrderHistoryModel> {
            override fun onSuccess(result: OrderHistoryModel) {
                Utilz.closeDialog()
                if (result.status!!.contains(AppConstants.TRUE)) {
                    RVproduct.adapter = OrderHistoryAdapter(this@OrderHistory, result.data!!, intent.getStringExtra("name"), R.layout.product_list_row, object : OnItemClickListener {
                        override fun onClick(pos: Int, text: String) {
                        }

                    })
                }else{
//                    Toast.makeText(this@OrderHistory, "" + result.msg, Toast.LENGTH_SHORT).show()
                    noorderfound.visibility = View.VISIBLE
                }
            }
            override fun onFailure(error: String) {
                Utilz.closeDialog()
                Toast.makeText(this@OrderHistory, "" + error, Toast.LENGTH_SHORT).show()
            }

            override fun onUnauthorized(errorNumber: Int) {
                Utilz.closeDialog()
                Toast.makeText(this@OrderHistory, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}