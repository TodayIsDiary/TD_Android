package dsm.todayisdiary.android.td_android.data.response.diary

import com.google.gson.annotations.SerializedName

data class DiaryLatestResponse(
    @SerializedName("list") val diarys: List<Diary>,
)
