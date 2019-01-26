package apextechies.starbasketseller.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import apextechies.starbasketseller.R
import apextechies.starbasketseller.fragment.OrderHistoryFragment
import kotlinx.android.synthetic.main.activity_productlist.*
import kotlinx.android.synthetic.main.common_toolbar.*

class OrderHistory: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productlist)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = intent.getStringExtra("name")


        toolbar.setNavigationOnClickListener {
            finish()
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        goToFragment(OrderHistoryFragment(intent.getStringExtra("name")))

    }
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                goToFragment(OrderHistoryFragment(intent.getStringExtra("name")))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                goToFragment(OrderHistoryFragment(resources.getString(R.string.competed_order)))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                goToFragment(OrderHistoryFragment(resources.getString(R.string.cancel_order)))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun goToFragment(orderHistoryFragment: OrderHistoryFragment) {
        fragmentManager.beginTransaction().replace(R.id.frameLayout, orderHistoryFragment).commit()
    }


}