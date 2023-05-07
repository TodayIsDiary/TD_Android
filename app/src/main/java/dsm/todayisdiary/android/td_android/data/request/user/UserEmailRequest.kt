package dsm.todayisdiary.android.td_android.data.request.user

import com.google.gson.annotations.SerializedName

data class UserEmailRequest(
    @SerializedName("email") val email: String
)