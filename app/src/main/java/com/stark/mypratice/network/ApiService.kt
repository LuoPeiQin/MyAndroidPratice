package com.stark.mypratice.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.network
 * @ClassName: ApiService
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2021/9/26 11:25
 */
interface ApiService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>
}