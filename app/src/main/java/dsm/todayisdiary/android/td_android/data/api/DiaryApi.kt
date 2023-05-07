package dsm.todayisdiary.android.td_android.data.api

import dsm.todayisdiary.android.td_android.data.request.diary.DiaryCreateRequest
import dsm.todayisdiary.android.td_android.data.response.diary.DiaryCategoryResponse
import dsm.todayisdiary.android.td_android.data.response.diary.DiaryDetailResponse
import dsm.todayisdiary.android.td_android.data.response.diary.DiaryLatestResponse
import dsm.todayisdiary.android.td_android.data.response.diary.DiaryPopularResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface DiaryApi {

    @POST("diary/create")
    fun diaryCreate(@Body body: DiaryCreateRequest): Call<ResponseBody>

    @GET("diary/list")
    fun diaryLatestList(): Call<DiaryLatestResponse>

    @GET("diary/list/heart")
    fun diaryPopularList(): Call<DiaryPopularResponse>

    @GET("diary/list/category")
    fun diaryCategoryResponse(@Query("value") category: String): Call<DiaryCategoryResponse>

    @GET("diary/detail/{diary_id}")
    fun diaryDetailResponse(@Path("diary_id") diaryId: Long): Call<DiaryDetailResponse>
}