package com.android.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.base.R
import com.android.base.adapter.MyPageAdapter
import com.android.base.fragment.*
import kotlinx.android.synthetic.main.activity_custom.*


/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-06-28
 */
@SuppressLint("Registered")
class CustomActivity : AppCompatActivity() {


    private lateinit var pageAdapter: MyPageAdapter
    private lateinit var fragmentList: ArrayList<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)
        initView()
    }

    private fun initView() {
        fragmentList = arrayListOf()
        fragmentList.add(CustomFragment1())
        fragmentList.add(CustomFragment2())
        fragmentList.add(CustomFragment3())
        fragmentList.add(CustomFragment4())
        fragmentList.add(CustomFragment5())
        fragmentList.add(CustomFragment6())
        fragmentList.add(CustomFragment8())
        fragmentList.add(CustomFragment7())
        for (index in 0 until fragmentList.size) {
            tabLayout.addTab(tabLayout.newTab())
        }
        pageAdapter = MyPageAdapter(supportFragmentManager, fragmentList)
        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = pageAdapter

        for ((index, _) in fragmentList.withIndex()) {
            tabLayout.getTabAt(index)?.text = "自定义View$index"
        }
    }

}