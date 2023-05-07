package dsm.todayisdiary.android.td_android.data.response.diary

import com.google.gson.annotations.SerializedName

data class DiaryDetailResponse(
    @SerializedName("board_id") val boardId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("category") val category: String,
    @SerializedName("board_time") val boardTime: String,
    @SerializedName("writer") val writer: String,
    @SerializedName("heart") val heart: Int,
    @SerializedName("comment_count") val commentCount: Int,
    @SerializedName("user_id") val userId: Int,
    @SerializedName("image_url") val imageUrl: String?,
    @SerializedName("writer_profile") val writerProfile: String,
    @SerializedName("liked") val liked: Boolean
)