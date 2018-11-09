package apextechies.starbasketseller.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.adapter.ProductVarientDeleteAdapter
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.InsertProductModel
import apextechies.starbasketseller.model.ProductUnitDetailsModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_addproduct.*
import kotlinx.android.synthetic.main.activity_producteditdeletelist.*
import kotlinx.android.synthetic.main.common_search_toolbar.*

class ProductVarientDeleteAcvtivity : AppCompatActivity() {
    private var retrofitDataProvider: RetrofitDataProvider? = null
    lateinit var all: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_producteditdeletelist)
        retrofitDataProvider = RetrofitDataProvider(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        RVproduct.layoutManager = LinearLayoutManager(this)

        getData()
        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun getData() {
        val list = intent.getParcelableArrayListExtra<ProductUnitDetailsModel>("list")

        tvProductName.text = "Product Name: "+intent.getStringExtra("name")
        tvUnitCount.text = "Varient: "+list.size.toString()
        RVproduct.adapter = ProductVarientDeleteAdapter(this, list, R.layout.productvarientdelete_row, object : OnItemClickListener {
            override fun onClick(pos: Int, text: String) {
                if (text.equals("edit")) {
                    startActivity(Intent(this@ProductVarientDeleteAcvtivity, AddProductAcvtivity::class.java)
                            .putExtra("actual_price", list[pos].actual_price)
                            .putExtra("selling_price", list[pos].selling_price)
                            .putExtra("unit", list[pos].varient)
                            .putExtra("productQuantity", list[pos].unit)
                            .putExtra("discount", list[pos].discount)
                            .putExtra("brand", intent.getStringExtra("brand"))
                            .putExtra("short_description", list[pos].short_description)
                            .putExtra("full_description", list[pos].full_description)
                            .putExtra("id", list[pos].id)
                            .putExtra("prod_id", list[pos].prod_id)
                            .putExtra("name", intent.getStringExtra("name"))
                            .putExtra("operation", "update")
                    )
                } else if (text.equals("delete")) {
                    if (list.size>1)  all ="yes"
                    else all = ""
                    retrofitDataProvider!!.deleteProduct(list[pos].id, list[pos].prod_id, all, object : DownlodableCallback<InsertProductModel> {
                        override fun onSuccess(result: InsertProductModel) {
                            if (result.status!!.contains(AppConstants.TRUE)) {
                                Utilz.closeDialog()
                                finish()
                            } else {
                                Toast.makeText(this@ProductVarientDeleteAcvtivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(error: String) {
                            Utilz.closeDialog()
                            Toast.makeText(this@ProductVarientDeleteAcvtivity, "" + error, Toast.LENGTH_SHORT).show()
                        }

                        override fun onUnauthorized(errorNumber: Int) {
                            Utilz.closeDialog()
                            Toast.makeText(this@ProductVarientDeleteAcvtivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }

        })
    }
}