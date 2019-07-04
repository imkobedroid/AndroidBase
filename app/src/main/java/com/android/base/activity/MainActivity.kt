package com.android.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.base.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {

        handler.setOnClickListener {
            startActivity(Intent(this@MainActivity, HandlerActivity::class.java))
        }
        progressInfo.setOnClickListener {
            startActivity(Intent(this@MainActivity, AidlActivity::class.java))
        }
        dataStructure.setOnClickListener {
            startActivity(Intent(this@MainActivity, DataStruActivity::class.java))
        }
        event.setOnClickListener {
            startActivity(Intent(this@MainActivity, EventActivity::class.java))
        }

        custom.setOnClickListener {
            startActivity(Intent(this@MainActivity, CustomActivity::class.java))
        }

        disAll.setOnClickListener {
            startActivity(Intent(this@MainActivity, DisAllActivity::class.java))
        }
    }
}
