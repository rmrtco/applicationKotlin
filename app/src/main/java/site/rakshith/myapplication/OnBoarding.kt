package site.rakshith.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager

class OnBoarding : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        /* hide actionbar */
        supportActionBar?.hide()

    }

    override fun onDestroy() {
        /* set onboarding is over */
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().apply(){
            putBoolean("IS_ONBOARDING_OVER",true)
            apply()
        }
        super.onDestroy()
    }
}
