package dsm.todayisdiary.android.td_android.data.api

import dsm.todayisdiary.android.td_android.data.request.user.UserEmailRequest
import dsm.todayisdiary.android.td_android.data.request.user.UserLoginRequest
import dsm.todayisdiary.android.td_android.data.request.user.UserSignUpRequest
import dsm.todayisdiary.android.td_android.data.response.UserLoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("user/login")
    fun userLogin(@Body body: UserLoginRequest): Call<UserLoginResponse>

    @POST("user/signup")
    fun userSignUp(@Body body: UserSignUpRequest)

    @POST("user/email")
    fun userEmail(@Body body: UserEmailRequest)
}