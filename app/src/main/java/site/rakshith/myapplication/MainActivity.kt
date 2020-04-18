package site.rakshith.myapplication

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* hide action bar */
        supportActionBar?.hide()


    }

    override fun onStart() {
        Handler().postDelayed(
            {
                /*
                 * check onboarding and action based on result
                 * also make sure that using androidx.preference.PreferenceManager
                 */

                PreferenceManager.getDefaultSharedPreferences(applicationContext).apply {
                    if(!getBoolean("IS_ONBOARDING_OVER",false)){
                        startActivity(Intent(applicationContext,OnBoarding::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@MainActivity,"Onboarding is over",Toast.LENGTH_SHORT).show()
                    }
                }

            },1000
        )
        super.onStart()
    }
}
