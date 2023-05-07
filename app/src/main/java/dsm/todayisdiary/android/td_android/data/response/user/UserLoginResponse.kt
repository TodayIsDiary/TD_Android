package dsm.todayisdiary.android.td_android.data.response.user

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(
    @SerializedName("atk") val accessToken: String,
    @SerializedName("rtk") val refreshToken: String
)
