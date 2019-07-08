package com.android.base.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.base.R
import kotlinx.android.synthetic.main.fragment_custom_6.view.*

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-03
 */
class CustomFragment6 : Fragment() {
    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_custom_6, null)
        view.CustomView5.setOnClickListener { }
        return view
    }
}