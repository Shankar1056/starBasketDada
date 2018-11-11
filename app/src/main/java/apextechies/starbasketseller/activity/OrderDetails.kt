package apextechies.starbasketseller.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.adapter.OrderDetailsdapter
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.AddressModel
import apextechies.starbasketseller.model.OrderDetailsModel
import apextechies.starbasketseller.model.OrderetailsDataModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_productlist.*
import kotlinx.android.synthetic.main.common_toolbar.*

class OrderDetails : AppCompatActivity() {

    var retrofitDataProvider: RetrofitDataProvider? = null

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
        retrofitDataProvider!!.getrerDetails(intent.getStringExtra("orderid"), intent.getStringExtra("address_id"), object : DownlodableCallback<OrderDetailsModel> {
            override fun onSuccess(result: OrderDetailsModel) {
                Utilz.closeDialog()
                RVproduct.adapter = OrderDetailsdapter(result.data!!)
                setDeliveryAddress(result.data!![0].adress)
                setTotalPrice(result.data!!)
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

    private fun setTotalPrice(data: ArrayList<OrderetailsDataModel>) {
        var price =0
        if (data.size>0){
            priceView.visibility = View.VISIBLE
            amount.visibility = View.VISIBLE

            for (i in 0 until data.size){
                price = price+ (Integer.parseInt(data[i].price) * Integer.parseInt(data[i].quantity))
            }

            textTotalAmount.setText(price.toString())
        }
    }

    private fun setDeliveryAddress(ad: ArrayList<AddressModel>?) {
        if (ad!!.size>0) {
            dliveryAdress.setText(ad!![0].name + "\n" + ad!![0].address1 + "," + ad!![0].address2 + "," + ad!![0].city + "," + ad!![0].pincode)
        }
    }
}