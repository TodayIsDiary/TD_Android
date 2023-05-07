package dsm.todayisdiary.android.td_android.data.request.diary

import com.google.gson.annotations.SerializedName
import dsm.todayisdiary.android.td_android.data.enums.CategoryType

data class DiaryCreateRequest(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("category") val category: CategoryType,
    @SerializedName("image_url") val imageUrl: String?
    )