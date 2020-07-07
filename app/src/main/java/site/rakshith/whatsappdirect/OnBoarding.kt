package site.rakshith.whatsappdirect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import site.rakshith.whatsappdirect.adapter.OnBoardAdapter
import site.rakshith.whatsappdirect.pojo.OnboardItem

class OnBoarding : AppCompatActivity() {
    var items = ArrayList<OnboardItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val nextBtn = findViewById<Button>(R.id.nextBtn)
        val skipBtn = findViewById<Button>(R.id.skipBtn)
        val launchAppBtn = findViewById<Button>(R.id.launchAppBtn)

        /* hide actionbar */
        supportActionBar?.hide()

        skipBtn.setOnClickListener {
            startActivity(Intent(applicationContext,HomeActivity::class.java))
            finish()
        }

        addItems()

        val viewPager = findViewById<ViewPager2>(R.id.onBoardViewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabIndicator)
        val onBoardAdapter = OnBoardAdapter(this,items)

        viewPager.adapter = onBoardAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
        }.attach()

        nextBtn.run {

            viewPager.adapter = onBoardAdapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            }.attach()

            nextBtn.setOnClickListener {
                if(viewPager.currentItem < items.size){
                    viewPager.currentItem = viewPager.currentItem + 1
                }
            }

            launchAppBtn.setOnClickListener{
                startActivity(Intent(applicationContext,HomeActivity::class.java))
                finish()
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when(position){
                    items.size-1 ->{
                        nextBtn.visibility = View.GONE
                        skipBtn.visibility = View.GONE
                        launchAppBtn.visibility = View.VISIBLE
                    }
                    else -> {
                        launchAppBtn.visibility = View.GONE
                        nextBtn.visibility = View.VISIBLE
                        skipBtn.visibility = View.VISIBLE
                    }

                }
            }

        })
    }

    override fun onDestroy() {
        /* set onboarding is over */
        PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().apply(){
            putBoolean("IS_ONBOARDING_OVER",true)
            apply()
        }
        super.onDestroy()
    }


    private fun addItems(){
        items.add(
            OnboardItem(R.drawable.onboard1
                ,"Whatsapp Direct"
                , "This application enables you to send WhatsApp Message without saving recipient number"
                , R.color.colorAccent))
        items.add(
            OnboardItem(R.drawable.onboard2
                ,"Step 1"
                , "Select Recipient Country and type recipient Phone number. By default country is set on device time zone"
                , R.color.colorAccent))
        items.add(
            OnboardItem(R.drawable.onboard3
                ,"Step 2"
                , "Type the message, this step is optional skip this step if you intend to send a file"
                , R.color.colorAccent))
        items.add(
            OnboardItem(R.drawable.onboard4
                ,"Step 3"
                , "Click send, this will open Chat with recipient number on WhatsApp, now you can chat with this person"
                , R.color.colorAccent))
    }
}

