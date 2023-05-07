package dsm.todayisdiary.android.td_android.diary

import dsm.todayisdiary.android.td_android.databinding.ActivityPostBinding
import dsm.todayisdiary.android.td_android.user.LoginActivity
import dsm.todayisdiary.android.td_android.user.SignUpActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.marginTop
import com.bumptech.glide.Glide

class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)

        val boardId = intent.getIntExtra("boardId", 0)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val category = intent.getStringExtra("category")
        val boardTime = intent.getStringExtra("boardTime")
        val writer = intent.getStringExtra("writer")
        val heart = intent.getIntExtra("heart", 0)
        val commentCount = intent.getIntExtra("commentCount", 0)
        val userId = intent.getIntExtra("userId", 0)
        val imageUrl = intent.getStringExtra("imageUrl")
        val writerProfile = intent.getStringExtra("writerProfile")
        val liked = intent.getBooleanExtra("liked", false)

        binding.title.text = title
        binding.content.text = content
        binding.category.text = category
        binding.boardTime.text = boardTime
        binding.writer.text = writer
        binding.heart.text = heart.toString()
        binding.commentCount.text = commentCount.toString()
        if (imageUrl != null) {
            Glide.with(binding.imageUrl)
                .load(imageUrl)
                .into(binding.imageUrl)
            binding.imageUrl.marginTop.plus(30)
        }
        Glide.with(binding.writerProfile)
            .load(writerProfile)
            .into(binding.writerProfile)

        setContentView(binding.root)

        Log.d("margin", "${binding.imageUrl.marginTop}")
    }
}