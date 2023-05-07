package dsm.todayisdiary.android.td_android.data.api

import dsm.todayisdiary.android.td_android.data.request.user.UserEmailCheckRequest
import dsm.todayisdiary.android.td_android.data.request.user.UserEmailRequest
import dsm.todayisdiary.android.td_android.data.request.user.UserLoginRequest
import dsm.todayisdiary.android.td_android.data.request.user.UserSignUpRequest
import dsm.todayisdiary.android.td_android.data.response.user.UserLoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @POST("user/login")
    fun userLogin(@Body body: UserLoginRequest): Call<UserLoginResponse>

    @POST("user/signup/add")
    fun userSignUp(@Body body: UserSignUpRequest): Call<ResponseBody>

    @POST("user/email")
    fun userEmail(@Body body: UserEmailRequest): Call<ResponseBody>

    @POST("user/signup/check")
    fun userEmailCheck(@Body body: UserEmailCheckRequest): Call<Boolean>

    @GET("user/check")
    fun userIdCheck(@Query("accountId") accountId: String): Call<Boolean>
}