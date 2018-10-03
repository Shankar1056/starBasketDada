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
import android.text.Editable
import android.text.TextWatcher
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.CategoryDateModel
import java.util.*


class ListingActivity : AppCompatActivity() {
    private var retrofitDataProvider: RetrofitDataProvider? = null
    var pos: Int = 0
    var adapter: CatListingAdapter?= null
    var subadapter: SubCatListingAdapter?= null
    var subsubadapter: SubSubCatListingAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        retrofitDataProvider = RetrofitDataProvider(this)
        commonRV.layoutManager = LinearLayoutManager(this)
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
        toolbar.setNavigationOnClickListener {
            val intent = Intent()
            intent.putExtra("ID", 0)
            setResult(2, intent)
            finish()
        }

        searchET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = searchET.getText().toString().toLowerCase(Locale.getDefault())
                if (pos == 1) adapter!!.filter(text)
                if (pos == 2) subadapter!!.filter(text)
                if (pos == 3) subsubadapter!!.filter(text)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun getProductCategory() {
        var list = ArrayList<CategoryDateModel>()
        Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.category(object : DownlodableCallback<CategoryModel> {
            override fun onSuccess(result: CategoryModel) {
                if (result.status!!.contains(AppConstants.TRUE)) {
                    Utilz.closeDialog()
                    for (i in 0 until result.data!!.size){

                        if (result.data!![i].status.equals("1")){
                            list.add(result.data!![i])
                        }
                    }
                    adapter = CatListingAdapter(this@ListingActivity, list, R.layout.listing_item, object : OnItemClickListener{
                        override fun onClick(pos: Int, text: String) {
                            for(i in 0 until result.data!!.size){
                                if (result.data!![i].name.equals(text)){
                                    sendDataBack(result.data!![i].id, result.data!![i].name)
                                }
                            }

                        }

                    })
                    commonRV.adapter = adapter
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


    private fun getProductSubCategory() {
        Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
        retrofitDataProvider!!.subCategory(intent.getStringExtra("id"),object : DownlodableCallback<SubCategoryModel> {
            override fun onSuccess(result: SubCategoryModel) {
                Utilz.closeDialog()
                if (result.status!!.contains(AppConstants.TRUE)) {
                    subadapter = SubCatListingAdapter(this@ListingActivity, result.data!!, R.layout.listing_item, object : OnItemClickListener{
                        override fun onClick(pos: Int, text: String) {
                            for(i in 0 until result.data!!.size){
                                if (result.data!![i].name.equals(text)){
                                    sendDataBack(result.data!![i].id, result.data!![i].name)
                                }
                            }

                        }

                    })
                    commonRV.adapter = subadapter
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
                    subsubadapter = SubSubCatListingAdapter(this@ListingActivity, result.data!!, R.layout.listing_item, object : OnItemClickListener{
                        override fun onClick(pos: Int, text: String) {
                            for(i in 0 until result.data!!.size){
                                if (result.data!![i].name.equals(text)){
                                    sendDataBack(result.data!![i].id, result.data!![i].name)
                                }
                            }
                        }

                    })
                    commonRV.adapter = subsubadapter
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
        val intent = Intent()
        intent.putExtra("ID", "0")
        setResult(2, intent)
        super.onBackPressed()
        //finish()//finishing activity`
    }
}