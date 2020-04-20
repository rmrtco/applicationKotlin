package site.rakshith.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.preference.PreferenceManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import site.rakshith.myapplication.adapter.OnBoardAdapter
import site.rakshith.myapplication.pojo.OnboardItem

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

            setOnClickListener {

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
                ,"Header 1"
                , "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                , R.color.colorAccent))
        items.add(
            OnboardItem(R.drawable.onboard2
                ,"Header 2"
                , "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                , R.color.colorAccent))
        items.add(
            OnboardItem(R.drawable.onboard3
                ,"Header 3"
                , "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                , R.color.colorAccent))
        items.add(
            OnboardItem(R.drawable.onboard4
                ,"Header 4"
                , "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
                , R.color.colorAccent))
    }
}

