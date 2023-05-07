package dsm.todayisdiary.android.td_android.diary

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import dsm.todayisdiary.android.td_android.data.RetrofitBuilder
import dsm.todayisdiary.android.td_android.data.enums.CategoryType
import dsm.todayisdiary.android.td_android.data.request.diary.DiaryCreateRequest
import dsm.todayisdiary.android.td_android.data.util.MyApplication
import dsm.todayisdiary.android.td_android.databinding.ActivityCreateDiaryBinding
import dsm.todayisdiary.android.td_android.dialog.CategoryDialog
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class CreateDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateDiaryBinding

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitBuilder = RetrofitBuilder

        val date: LocalDateTime = LocalDateTime.now()

        var category = CategoryType.HAPPY
        var imageUrl: String? = null

        binding.date.text = date.format(
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 (E)").withLocale(
                Locale.forLanguageTag("ko")
            )
        )

        binding.categoryButton.setOnClickListener {
            val dialog = CategoryDialog()

            dialog.setCategoryClickListener(object : CategoryDialog.OnCategoryClickListener {
                override fun onHappyClicked() {
                    binding.categoryText.text = "기쁨"
                    category = CategoryType.HAPPY
                }

                override fun onSadClicked() {
                    binding.categoryText.text = "슬픔"
                    category = CategoryType.SAD
                }

                override fun onAngryClicked() {
                    binding.categoryText.text = "화남"
                    category = CategoryType.ANGRY
                }

                override fun onSurpriseClicked() {
                    binding.categoryText.text = "놀람"
                    category = CategoryType.SURPRISE
                }
            })
            dialog.show(supportFragmentManager, "CategoryDialog")
        }

        binding.checkButton.setOnClickListener {
            Log.d(
                "title, content, category, imageUrl",
                "\ntitle ${binding.title.text} \ncontent ${binding.content.text} \ncategory $category \nimageUrl $imageUrl"
            )
            Log.d(
                "atk",
                MyApplication.prefs.getString("atk", "")
            )
            retrofitBuilder.diaryApi.diaryCreate(
                DiaryCreateRequest(
                    binding.title.text.toString(),
                    binding.content.text.toString(),
                    category,
                    imageUrl
                )
            ).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(Call: Call<ResponseBody>, t: Throwable) {
                    Log.d("실패", "${t.message}")
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        Log.d("성공", response.body()!!.toString())
                    }
                }
            })
        }
    }
}