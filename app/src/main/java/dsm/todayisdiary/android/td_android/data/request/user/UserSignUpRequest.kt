package dsm.todayisdiary.android.td_android.data.request.user

import com.google.gson.annotations.SerializedName
import dsm.todayisdiary.android.td_android.data.enums.SexType

data class UserSignUpRequest (
    @SerializedName("account_id") val accountId: String,
    @SerializedName("nick_name") val nickName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("password_valid") val passwordValid: String,
    @SerializedName("code") val code: Int,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("sex") val sex: SexType,
    @SerializedName("introduction") val introduction: String,
)