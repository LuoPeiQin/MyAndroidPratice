package com.stark.mypratice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.stark.mypratice.ipcdemo.IPCDemoActivity
import com.stark.mypratice.network.ApiService
import com.stark.mypratice.network.Repo
import com.stark.mypratice.viewevent.ViewEventActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    fun viewEventActivity(view: View) {
//        startActivity(Intent(this, ViewEventActivity::class.java))
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)
        apiService.listRepos("LuoPeiQin").enqueue(object : Callback<List<Repo>>{
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                println("response = ${(response.body())!![0].name}")
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
            }

        })
    }
}