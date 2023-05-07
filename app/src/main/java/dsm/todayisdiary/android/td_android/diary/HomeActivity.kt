package dsm.todayisdiary.android.td_android.diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import dsm.todayisdiary.android.td_android.data.util.ViewPagerAdapter
import dsm.todayisdiary.android.td_android.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val tabTextList = listOf("최신", "인기", "기쁨", "슬픔", "화남", "놀람")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1.FragmentStateAdapter 초기화
        val pagerAdapter = ViewPagerAdapter(this)
            .apply {
                addFragment(HomeLatestFragment())
                addFragment(HomePopularFragment())
                addFragment(HomeHappyFragment())
                addFragment(HomeSadFragment())
                addFragment(HomeAngryFragment())
                addFragment(HomeSurpriseFragment())
            }

        // 2.ViewPager2의 Adapter 설정
        val viewPager: ViewPager2 = binding.viewPager.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    Log.d("ViewPagerFragment", "Page ${position + 1}")
                }
            })
        }

        // 3.TabLayout과 ViewPager 연결
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()


        binding.profile.setOnClickListener {
        }

        binding.createDiary.setOnClickListener {
            startActivity(Intent(this@HomeActivity, CreateDiaryActivity::class.java))
        }
    }
}