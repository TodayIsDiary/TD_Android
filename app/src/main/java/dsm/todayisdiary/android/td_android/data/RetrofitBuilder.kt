package dsm.todayisdiary.android.td_android.data

import dsm.todayisdiary.android.td_android.data.api.BASE_URL
import dsm.todayisdiary.android.td_android.data.api.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApi: UserApi = retrofit.create(UserApi::class.java)
}