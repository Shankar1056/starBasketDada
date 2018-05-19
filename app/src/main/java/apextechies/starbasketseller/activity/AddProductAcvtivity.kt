package apextechies.starbasketseller.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.adapter.CatListingAdapter
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.CategoryModel
import apextechies.starbasketseller.model.InsertProductModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_addproduct.*
import kotlinx.android.synthetic.main.common_search_toolbar.*
import java.text.SimpleDateFormat
import java.util.*


class AddProductAcvtivity : AppCompatActivity() {
    private var retrofitDataProvider: RetrofitDataProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addproduct)

        initWidgit()

    }

    private fun initWidgit() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        retrofitDataProvider = RetrofitDataProvider(this)

        submit.setOnClickListener {
            validateAndInsert()
        }

        toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun validateAndInsert() {
        val c = Calendar.getInstance().getTime()
        val df = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = df.format(c)

        if (TextUtils.isEmpty(productName.text.toString().trim())) Utilz.showToast(this, "Enter your product name")
        else if (TextUtils.isEmpty(productUnit.text.toString().trim())) Utilz.showToast(this, "Enter quantity")
        else if (TextUtils.isEmpty(productActual_price.text.toString().trim())) Utilz.showToast(this, "Enter product actual price")
        else if (TextUtils.isEmpty(productSelling_price.text.toString().trim())) Utilz.showToast(this, "Enter product selling price")
        else if (TextUtils.isEmpty(productShortDescription.text.toString().trim())) Utilz.showToast(this, "Enter product short description")
        else if (TextUtils.isEmpty(productFullDescription.text.toString().trim())) Utilz.showToast(this, "Describe about your product")
        else{
            Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
            retrofitDataProvider!!.insertProduct(intent.getStringExtra("sub_cat_id"), intent.getStringExtra("sub_sub_cat_id"),
                    productName.text.toString(), productUnit.text.toString(), productActual_price.text.toString(), productSelling_price.text.toString(), "10", productShortDescription.text.toString(),
                    productFullDescription.text.toString(), "1", formattedDate,object : DownlodableCallback<InsertProductModel> {
                override fun onSuccess(result: InsertProductModel) {
                    if (result.status!!.contains(AppConstants.TRUE)) {
                        Utilz.closeDialog()
                    }else{
                        Toast.makeText(this@AddProductAcvtivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(error: String) {
                    Utilz.closeDialog()
                    Toast.makeText(this@AddProductAcvtivity, "" + error, Toast.LENGTH_SHORT).show()
                }

                override fun onUnauthorized(errorNumber: Int) {
                    Utilz.closeDialog()
                    Toast.makeText(this@AddProductAcvtivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }
}