package dsm.todayisdiary.android.td_android.data.response.diary

import com.google.gson.annotations.SerializedName

data class Diary(
    @SerializedName("board_id") val boardId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("category") val category: String,
    @SerializedName("data") val data: String,
    @SerializedName("view") val view: Int,
    @SerializedName("writer_profile") val writerProfile: String,
    @SerializedName("comment_count") val commentCount: Int,
    @SerializedName("image_url") val imageUrl: String?
)