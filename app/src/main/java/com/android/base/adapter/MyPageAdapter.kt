package com.android.base.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-03
 */
class MyPageAdapter(fragmentManager: FragmentManager, private val fragmentList: List<Fragment>) :
    FragmentPagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}