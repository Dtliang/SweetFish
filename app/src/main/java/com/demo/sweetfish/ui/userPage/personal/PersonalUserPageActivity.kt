package com.demo.sweetfish.ui.userPage.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.demo.sweetfish.SweetFishApplication
import com.demo.sweetfish.ui.component.WithInitEventViewPager2Adapter
import com.demo.sweetfish.ui.userPage.UserPageTagLayout
import com.example.sweetfish.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PersonalUserPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_user_page)
        initViewPager()
        initNavigationBar()
    }

    private fun initViewPager() {
        val viewPager: ViewPager2 =
            findViewById(R.id.activity_personal_user_page_userrelations_viewpage)
        val tabLayout: TabLayout =
            findViewById(R.id.activity_personal_user_page_userrelations_viewpage_tablayout)
        val views: List<Pair<View, (View) -> Unit>> = listOf(
            Pair(
                LayoutInflater.from(SweetFishApplication.context).inflate(
                    R.layout.activity_personal_user_page_userrelations_viewpage_goodsview, null
                ), ::initRelationGoodsView
            ), Pair(
                LayoutInflater.from(SweetFishApplication.context).inflate(
                    R.layout.activity_personal_user_page_userrelations_viewpage_appriseview, null
                ), ::initRelationAppraiseView
            )
        )
        viewPager.adapter = WithInitEventViewPager2Adapter(views)
        val tabTextList: List<String> = listOf("宝贝", "评价")
        TabLayoutMediator(
            tabLayout, viewPager
        ) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()
    }

    private fun initRelationGoodsView(viewItem: View) {
        val tagLayout = viewItem.findViewById<UserPageTagLayout>(R.id.UserPageGoodsTagLayout)
        tagLayout.init(mutableMapOf("All" to "全部", "InSell" to "在卖", "SoldOut" to "卖光")) {

        }
        tagLayout.forceSelectWithEvent("All")
    }

    private fun initRelationAppraiseView(viewItem: View) {
        val tagLayout = viewItem.findViewById<UserPageTagLayout>(R.id.UserPageAppriseTagLayout)
        tagLayout.init(mutableMapOf()) {

        }
//        tagLayout.forceSelectWithEvent("")
    }

    private fun initNavigationBar() {
        val navigation = findViewById<LinearLayout>(R.id.UserPageTopNavigationBar)
        val scrollView = findViewById<ScrollView>(R.id.UserPageScrollView)
        scrollView.setOnScrollChangeListener { _, _, newY, _, _ ->
            val percent: Float = if (newY > 400) {
                0f
            } else {
                1.0f - newY / 400.0f
            }
            navigation.alpha = percent
        }
        navigation.findViewById<ImageView>(R.id.UserPageNavigationBarReturnButton)
            .setOnClickListener {
                finish()
            }
    }
}