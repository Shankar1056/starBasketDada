package apextechies.starbasketseller.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.adapter.CatListingAdapter
import apextechies.starbasketseller.adapter.SubCatListingAdapter
import apextechies.starbasketseller.adapter.SubSubCatListingAdapter
import apextechies.starbasketseller.allinterface.OnItemClickListener
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.model.CategoryModel
import apextechies.starbasketseller.model.SubCategoryModel
import apextechies.starbasketseller.model.SubSubCategoryModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_listing.*
import kotlinx.android.synthetic.main.common_search_toolbar.*
import android.content.Intent
import apextechies.starbasketseller.common.Utilz


class ListingActivity : AppCompatActivity() {
    private var retrofitDataProvider: RetrofitDataProvider? = null
    var pos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        retrofitDataProvider = RetrofitDataProvider(this)
        commonRV.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?

        try {
            pos = intent.getIntExtra("pos", 0)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        if (pos==1){
            getProductCategory()
        }
        else if (pos==2){
            getProductSubCategory()
        }else if (pos == 3) {
            getProductSubSubCategory()
        }

        //searchET.addTextChangedListener(object in )
    }

    private fun getProductCategory() {
        Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.category(object : DownlodableCallback<CategoryModel> {
            override fun onSuccess(result: CategoryModel) {
                if (result.status!!.contains(AppConstants.TRUE)) {
                    Utilz.closeDialog()
                    commonRV.adapter = CatListingAdapter(this@ListingActivity, result.data!!, R.layout.listing_item, object : OnItemClickListener{
                        override fun onClick(pos: Int) {
                            sendDataBack(result.data!![pos].id, result.data!![pos].name)
                        }

                    })
                }else{
                    Toast.makeText(this@ListingActivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                }g
            }
            override fun onFailure(error: String) {
                Utilz.closeDialog()
                Toast.makeText(this@ListingActivity, "" + error, Toast.LENGTH_SHORT).show()
            }

            override fun onUnauthorized(errorNumber: Int) {
                Utilz.closeDialog()
                Toast.makeText(this@ListingActivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun getProductSubCategory() {
        Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.subCategory(intent.getStringExtra("id"),object : DownlodableCallback<SubCategoryModel> {
            override fun onSuccess(result: SubCategoryModel) {
                Utilz.closeDialog()
                if (result.status!!.contains(AppConstants.TRUE)) {
                    commonRV.adapter = SubCatListingAdapter(this@ListingActivity, result.data!!, R.layout.listing_item, object : OnItemClickListener{
                        override fun onClick(pos: Int) {
                            sendDataBack(result.data!![pos].id, result.data!![pos].name)
                        }

                    })
                }else{
                    Toast.makeText(this@ListingActivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(error: String) {
                Utilz.closeDialog()
                Toast.makeText(this@ListingActivity, "" + error, Toast.LENGTH_SHORT).show()
            }

            override fun onUnauthorized(errorNumber: Int) {
                Utilz.closeDialog()
                Toast.makeText(this@ListingActivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun getProductSubSubCategory() {
        Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.subSubCategory(intent.getStringExtra("id"),object : DownlodableCallback<SubSubCategoryModel> {
            override fun onSuccess(result: SubSubCategoryModel) {
                Utilz.closeDialog()
                if (result.status!!.contains(AppConstants.TRUE)) {
                    commonRV.adapter = SubSubCatListingAdapter(this@ListingActivity, result.data!!, R.layout.listing_item, object : OnItemClickListener{
                        override fun onClick(pos: Int) {
                            sendDataBack(result.data!![pos].id, result.data!![pos].name)
                        }

                    })
                }else{
                    Toast.makeText(this@ListingActivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(error: String) {
                Utilz.closeDialog()
                Toast.makeText(this@ListingActivity, "" + error, Toast.LENGTH_SHORT).show()
            }

            override fun onUnauthorized(errorNumber: Int) {
                Utilz.closeDialog()
                Toast.makeText(this@ListingActivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun sendDataBack(id: String?, name: String?) {
        val intent = Intent()
        intent.putExtra("ID", id)
        intent.putExtra("NAME", name)
        intent.putExtra("POS", pos)
        setResult(2, intent)
        finish()//finishing activity
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent()
        intent.putExtra("ID", 0)
        setResult(2, intent)
        finish()//finishing activity`
    }
}