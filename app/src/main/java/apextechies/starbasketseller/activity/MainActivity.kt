package apextechies.starbasketseller.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import apextechies.starbasketseller.R
import apextechies.starbasketseller.allinterface.OnClickListenr
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.splash.LoginActivity
import apextechies.starbasketseller.splash.Splash
import apextechies.starbasketseller.splash.SplashActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        var v = nav_view.getHeaderView(0)
        v.name.text = (ClsGeneral.getStrPreferences(this, AppConstants.USERNAME))
        v.email.text = (ClsGeneral.getStrPreferences(this, AppConstants.USEREMAIL))

        addproduct.setOnClickListener {
            startActivity(Intent(this@MainActivity,PreAddCategoryctivity::class.java))
        }
        newrderCV.setOnClickListener {
            startActivity(Intent(this@MainActivity,OrderHistory::class.java)
                    .putExtra("name", "New Order"))
        }
       /* completedorder.setOnClickListener {
            startActivity(Intent(this@MainActivity,OrderHistory::class.java)
                    .putExtra("name", "Completed Order"))
        }*/
        vieweitCV.setOnClickListener {
            startActivity(Intent(this@MainActivity,OrderHistory::class.java)
                    .putExtra("name", "View Edit"))
        }

    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_productList) {
            startActivity(Intent(this, ProductListActivity::class.java))
        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_ordermanagment) {

            startActivity(Intent(this@MainActivity, OrderHistory::class.java)
                    .putExtra("name", "Order History"))

        } else if (id == R.id.nav_paymentcollection) {

        } else if (id == R.id.nav_logout) {

            Utilz.displayMessageAlertWithCllbak("Are you sure you want to logged out from this app?", this, object : OnClickListenr {
                override fun onClick(posInt: Int) {
                    ClsGeneral.setPreferences(this@MainActivity, AppConstants.USERID, "")
//            FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finishAffinity()
                }
            })


        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
