package dsm.todayisdiary.android.td_android.data.request.user

import com.google.gson.annotations.SerializedName

data class UserEmailCheckRequest(
    @SerializedName("email") val email: String,
    @SerializedName("code") val code: String
)