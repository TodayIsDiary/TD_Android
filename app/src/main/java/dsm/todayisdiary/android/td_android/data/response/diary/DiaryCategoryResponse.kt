package dsm.todayisdiary.android.td_android.data.response.diary

import com.google.gson.annotations.SerializedName

data class DiaryCategoryResponse(
    @SerializedName("list") val diarys: List<Diary>,
)