package apextechies.starbasketseller.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.CompoundButton
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*


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
       var v= nav_view.getHeaderView(0)
        v.name.text = (ClsGeneral.getStrPreferences(this, AppConstants.USERNAME))
        v.email.text = (ClsGeneral.getStrPreferences(this, AppConstants.USEREMAIL))
        categoryET.setOnClickListener {
            movetoListingPage(1, cat_id)
        }
        subcategoryET.setOnClickListener {
            if (!TextUtils.isEmpty(cat_id)) movetoListingPage(2, cat_id)
            else Toast.makeText(this, "Select category first", Toast.LENGTH_SHORT).show()
        }
        subsubcategoryET.setOnClickListener {
            if (!TextUtils.isEmpty(subcat_id)) movetoListingPage(3, subcat_id)
            else Toast.makeText(this, "Select Sub category first", Toast.LENGTH_SHORT).show()
        }
        subCatTB.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if (isChecked) {
                    subcatText.text = resources.getString(R.string.have_subcat)
                    subcategoryET.isClickable = true
                    subcatText.alpha = 0.9F
                }
                else {
                    subcatText.text = resources.getString(R.string.have_nosubcat)
                    subCatTB.isChecked = false
                    subsubCatTB.isChecked = false
                    subsubcatText.text = resources.getString(R.string.have_nosubsubcat)
                    subcategoryET.isClickable = false
                    subsubcategoryET.isClickable = false
                    subcatText.alpha = 0.1F
                    subsubcatText.alpha = 0.1F
                    subcategoryET.text = "";
                    subsubcategoryET.text = "";
                }
            }

        })
        subsubCatTB.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

                if (subCatTB.isChecked == true) {
                    if (isChecked) {
                        subsubcatText.text = resources.getString(R.string.have_subsubcat)
                        subsubcatText.alpha = 0.9F
                        subsubcategoryET.isClickable = true
                    } else {
                        subsubcatText.text = resources.getString(R.string.have_nosubsubcat)
                        subsubcatText.alpha = 0.1F
                        subsubcategoryET.isClickable = false
                        subsubcategoryET.text = "";
                    }
                }else
                {
                    subsubCatTB.isChecked = false
                }
            }

        })

        addButton.setOnClickListener {
            startActivity(Intent(this, AddProductAcvtivity::class.java)
                    .putExtra("sub_cat_id", subcat_id)
                    .putExtra("sub_sub_cat_id", subSubcat_id)
                    .putExtra("operation", "newinsert")
            )
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



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        if (id == R.id.nav_productList) {
            startActivity(Intent(this, ProductListActivity::class.java))
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }/* else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
