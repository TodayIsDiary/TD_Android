package dsm.todayisdiary.android.td_android.data.request.user

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(
    @SerializedName("account_id") val accountId: String,
    @SerializedName("password") val password: String
)