package com.android.base.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.base.R

/**
 * 图片圆形裁剪
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-03
 */
class CustomFragment8 : Fragment() {
    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_custom_8, null)
    }
}