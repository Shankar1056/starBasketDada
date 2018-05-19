package apextechies.starbasketseller.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import apextechies.starbasketseller.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var cat_id: String? = null
    private var subcat_id: String? = null
    private var subSubcat_id: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        categoryET.setOnClickListener {
            movetoListingPage(1, cat_id)
        }
        subcategoryET.setOnClickListener {
            movetoListingPage(2, cat_id)
        }
        subsubcategoryET.setOnClickListener {
            movetoListingPage(3, subcat_id)
        }
    }

    private fun movetoListingPage(i: Int, id: String?) {
        startActivityForResult(Intent(this@MainActivity, ListingActivity::class.java).putExtra("pos", i).putExtra("id", id), 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
            var pos: Int = 0
            try {
                pos = data.getIntExtra("POS", 0)
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }
            if (pos==1){
                cat_id = data.getStringExtra("ID")
                categoryET.text = data.getStringExtra("NAME")
            }else if (pos == 2){
                subcat_id = data.getStringExtra("ID")
                subcategoryET.text = data.getStringExtra("NAME")
            }else if (pos == 3){
                subSubcat_id = data.getStringExtra("ID")
                subsubcategoryET.text = data.getStringExtra("NAME")
            }

        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
