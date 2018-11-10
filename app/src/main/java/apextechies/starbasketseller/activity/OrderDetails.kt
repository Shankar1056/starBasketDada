package apextechies.starbasketseller.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.adapter.OrderDetailsdapter
import apextechies.starbasketseller.adapter.OrderHistoryAdapter
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.OrderDetailsModel
import apextechies.starbasketseller.model.OrderHistoryModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_productlist.*
import kotlinx.android.synthetic.main.common_toolbar.*

class OrderDetails: AppCompatActivity() {

    var retrofitDataProvider: RetrofitDataProvider?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productlist)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Order Details"

        textDA.visibility = View.VISIBLE
        dliveryAdress.visibility = View.VISIBLE
        view.visibility = View.VISIBLE

        retrofitDataProvider = RetrofitDataProvider(this)
        RVproduct.layoutManager = LinearLayoutManager(this)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        getOrderDetails()
    }

    private fun getOrderDetails() {
        Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.getrerDetails(intent.getStringExtra("orderid"),object : DownlodableCallback<OrderDetailsModel> {
            override fun onSuccess(result: OrderDetailsModel) {
                Utilz.closeDialog()
                RVproduct.adapter = OrderDetailsdapter(result.data!!)
            }
            override fun onFailure(error: String) {
                Utilz.closeDialog()
                Toast.makeText(this@OrderDetails, "" + error, Toast.LENGTH_SHORT).show()
            }

            override fun onUnauthorized(errorNumber: Int) {
                Utilz.closeDialog()
                Toast.makeText(this@OrderDetails, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}