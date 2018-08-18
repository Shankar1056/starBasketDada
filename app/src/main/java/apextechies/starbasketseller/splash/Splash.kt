package apextechies.starbasketseller.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import apextechies.starbasketseller.R
import kotlinx.android.synthetic.main.splash.*

class Splash: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        signupCV.setOnClickListener {
            startActivity(Intent(this@Splash, SignUpActivity::class.java))
        }
        signupButton.setOnClickListener {
            startActivity(Intent(this@Splash, SignUpActivity::class.java))
        }
        loginCV.setOnClickListener {
            startActivity(Intent(this@Splash, LoginActivity::class.java))
        }
        submit.setOnClickListener {
            startActivity(Intent(this@Splash, LoginActivity::class.java))
        }
    }
}