package apextechies.starbasketseller.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import apextechies.starbasketseller.R
import apextechies.starbasketseller.activity.MainActivity
import apextechies.starbasketseller.common.AppConstants
import apextechies.starbasketseller.common.ClsGeneral
import apextechies.starbasketseller.common.Utilz
import apextechies.starbasketseller.model.LoginModel
import apextechies.starbasketseller.retrofit.DownlodableCallback
import apextechies.starbasketseller.retrofit.RetrofitDataProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity: AppCompatActivity() {
    var retrofitDataProvider: RetrofitDataProvider?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        retrofitDataProvider = RetrofitDataProvider(this)

        val c = Calendar.getInstance().getTime()
        val df = SimpleDateFormat("yyyy-MM-dd")
        val formattedDate = df.format(c)

        submit.setOnClickListener {
            if (email.text.toString().trim().equals("")) Utilz.showToast(this, "Enter your email")
            else if (!Utilz.isValidEmail1(email.text.toString())) Utilz.showToast(this, "Enter valid email")
            else if (password.text.toString().trim().equals("")) Utilz.showToast(this, "Enter your password")
            else{
                Utilz.showDailog(this, resources.getString(R.string.pleaee_wait))
                retrofitDataProvider!!.login(email.text.toString(), password.text.toString(), formattedDate, object : DownlodableCallback<LoginModel> {
                    override fun onSuccess(result: LoginModel) {
                        if (result.status!!.contains(AppConstants.TRUE)) {
                            Utilz.closeDialog()
                            ClsGeneral.setPreferences(this@LoginActivity, AppConstants.USERID, result.data!![0].id)
                            ClsGeneral.setPreferences(this@LoginActivity, AppConstants.USERNAME, result.data!![0].name)
                            ClsGeneral.setPreferences(this@LoginActivity, AppConstants.USEREMAIL, result.data!![0].email)
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finishAffinity()
                        }else{
                            Toast.makeText(this@LoginActivity, "" + result.msg, Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(error: String) {
                        Utilz.closeDialog()
                        Toast.makeText(this@LoginActivity, "" + error, Toast.LENGTH_SHORT).show()
                    }

                    override fun onUnauthorized(errorNumber: Int) {
                        Utilz.closeDialog()
                        Toast.makeText(this@LoginActivity, "Something went wrong, Please try again!!", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }


    }
}