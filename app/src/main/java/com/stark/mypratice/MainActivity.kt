package com.stark.mypratice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.stark.mypratice.ipcdemo.IPCDemoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun algorithmActivity(view: View) {
        startActivity(Intent(this, AlgorithmActivity::class.java));
    }

    fun ipcDemoActivity(view: View) {
        startActivity(Intent(this, IPCDemoActivity::class.java))
    }
}