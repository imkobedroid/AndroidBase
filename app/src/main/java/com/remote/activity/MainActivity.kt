package com.remote.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.remote.R
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

    }
}
