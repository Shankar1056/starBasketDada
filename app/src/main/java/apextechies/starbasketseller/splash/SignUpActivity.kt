package apextechies.starbasketseller.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.activity.MainActivity
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.LoginModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.common_search_toolbar.*
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : AppCompatActivity(){
    private var retrofitDataProvider: RetrofitDataProvider? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
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
        if (Name.text.toString().trim().equals("")) Utilz.showToast(this, "Enter your name")
        else if (email.text.toString().trim().equals("")) Utilz.showToast(this, "Enter your email")
        else if (!Utilz.isValidEmail1(email.text.toString())) Utilz.showToast(this, "Enter valid email")
        else if (business_name.text.toString().trim().equals("")) Utilz.showToast(this, "Enter your phone")
        else if (address.text.toString().trim().equals("")) Utilz.showToast(this, "Enter your phone")
        else{
            Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
            retrofitDataProvider!!.signup(Name.text.toString(), email.text.toString(), "", intent.getStringExtra("mobile"), business_name.text.toString(),  address.text.toString(), formattedDate, object : DownlodableCallback<LoginModel> {
                override fun onSuccess(result: LoginModel) {
                    if (result.status!!.contains(AppConstants.TRUE)) {
                        Utilz.closeDialog()
                        ClsGeneral.setPreferences(this@SignUpActivity, AppConstants.USERID, result.data!![0].id)
                        ClsGeneral.setPreferences(this@SignUpActivity, AppConstants.USERNAME, result.data!![0].name)
                        ClsGeneral.setPreferences(this@SignUpActivity, AppConstants.USEREMAIL, result.data!![0].email)
                        startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
                        finishAffinity()
                    }else{
                        Toast.makeText(this@SignUpActivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(error: String) {
                    Utilz.closeDialog()
                    Toast.makeText(this@SignUpActivity, "" + error, Toast.LENGTH_SHORT).show()
                }

                override fun onUnauthorized(errorNumber: Int) {
                    Utilz.closeDialog()
                    Toast.makeText(this@SignUpActivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}