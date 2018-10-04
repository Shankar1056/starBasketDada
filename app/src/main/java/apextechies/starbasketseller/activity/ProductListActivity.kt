package apextechies.starbasketseller.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.adapter.ProductListAdapter
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.ProductListModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_productlist.*
import kotlinx.android.synthetic.main.common_toolbar.*

class ProductListActivity: AppCompatActivity(){
    private var retrofitDataProvider: RetrofitDataProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productlist)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        retrofitDataProvider = RetrofitDataProvider(this)
        RVproduct.layoutManager = LinearLayoutManager(this)

        getProductList()

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getProductList() {
        Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.productList(ClsGeneral.getStrPreferences(this, AppConstants.USERID),object : DownlodableCallback<ProductListModel> {
            override fun onSuccess(result: ProductListModel) {
                Utilz.closeDialog()
                if (result.status!!.contains(AppConstants.TRUE)) {
                    RVproduct.adapter = ProductListAdapter(this@ProductListActivity, result.data!!, R.layout.product_list_row, object : OnItemClickListener {
                        override fun onClick(pos: Int, text: String) {
                        if (text.equals("edit")){
                            startActivity(Intent(this@ProductListActivity, ProductVarientDeleteAcvtivity::class.java)
                                    .putParcelableArrayListExtra("list", result.data!![pos].unitdetails)
                                    .putExtra("name", result.data!![pos].name)
                                    .putExtra("operation", "update")
                            )
                        }
                            else if (text.equals("delete")){
                            startActivity(Intent(this@ProductListActivity, ProductVarientDeleteAcvtivity::class.java)
                                    .putParcelableArrayListExtra("list", result.data!![pos].unitdetails)
                                    .putExtra("name", result.data!![pos].name)
                                    .putExtra("operation", "update")
                            )

                        }
                        else if (text.equals("add")){
                            startActivity(Intent(this@ProductListActivity, AddProductAcvtivity::class.java)
                                    .putExtra("id", "")
                                    .putExtra("prod_id", result.data!![pos].id)
                                    .putExtra("name", result.data!![pos].name)
                                    .putExtra("operation", "insert")
                            )
                        }
                        }

                    })
                }else{
                    //Toast.makeText(this@ProductListActivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                    noorderfound.visibility = View.VISIBLE
                }
            }
            override fun onFailure(error: String) {
                Utilz.closeDialog()
                Toast.makeText(this@ProductListActivity, "" + error, Toast.LENGTH_SHORT).show()
            }

            override fun onUnauthorized(errorNumber: Int) {
                Utilz.closeDialog()
                Toast.makeText(this@ProductListActivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
