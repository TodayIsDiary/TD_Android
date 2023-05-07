package dsm.todayisdiary.android.td_android.data

import dsm.todayisdiary.android.td_android.data.RetrofitBuilder.interceptor
import dsm.todayisdiary.android.td_android.data.api.BASE_URL
import dsm.todayisdiary.android.td_android.data.api.DiaryApi
import dsm.todayisdiary.android.td_android.data.api.UserApi
import dsm.todayisdiary.android.td_android.data.util.MyApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val interceptor = Interceptor { chain: Interceptor.Chain ->
        val original = chain.request()
        if (original.url.encodedPath.equals("/users/login", true)
            || original.url.encodedPath.equals("/users/signup", true)
            || original.url.encodedPath.equals("/users/email", true)
        ) {
            chain.proceed(original)
        } else {
            chain.proceed(original.newBuilder().apply {
                addHeader("Authorization", MyApplication.prefs.getString("atk", ""))
            }.build())
        }
    }


    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userApi: UserApi = retrofit.create(UserApi::class.java)
    val diaryApi: DiaryApi = retrofit.create(DiaryApi::class.java)
}