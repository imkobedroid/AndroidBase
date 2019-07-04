package com.android.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.base.R
import kotlinx.android.synthetic.main.activity_dis_all.*

/**
 * @author Dsh  imkobedroid@gmail.com
 * @date 2019-07-04
 */
@SuppressLint("Registered")
class DisAllActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dis_all)

        viewGroupE.setOnClickListener { }
        viewE.setOnClickListener { }

    }


//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//
//        when (event?.action) {
//            MotionEvent.ACTION_DOWN -> {
//                Log.v(LOG, "Activity-onTouchEvent  ACTION_DOWN")
//            }
//            MotionEvent.ACTION_MOVE -> {
//                Log.v(LOG, "Activity-onTouchEvent  ACTION_MOVE")
//            }
//            MotionEvent.ACTION_UP -> {
//                Log.v(LOG, "Activity-onTouchEvent  ACTION_UP")
//            }
//        }
//        val  result=super.onTouchEvent(event)
//        Log.v(LOG, "Activity-onTouchEvent-result  $result")
//        return result
//    }
}